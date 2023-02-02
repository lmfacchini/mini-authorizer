package com.vr.miniauthorizer.core.service;

import com.vr.miniauthorizer.bridge.constants.TransactionStatus;
import com.vr.miniauthorizer.bridge.exception.BusinessException;
import com.vr.miniauthorizer.bridge.service.AuthorizerService;
import com.vr.miniauthorizer.bridge.to.CardBalanceTO;
import com.vr.miniauthorizer.bridge.to.CardTO;
import com.vr.miniauthorizer.bridge.to.TransactionResultTO;
import com.vr.miniauthorizer.bridge.to.TransactionTO;
import com.vr.miniauthorizer.core.repository.CardRepository;
import com.vr.miniauthorizer.domain.CardVO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AuthorizerServiceImpl implements AuthorizerService {

    private CardRepository repository;

    public AuthorizerServiceImpl(CardRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CardTO createCard(CardTO to) {
        try{
            CardVO vo = new CardVO();
            vo.setBalance(BigDecimal.valueOf(500L));
            vo.setPassword(to.getPassword());
            vo.setNumber(to.getNumber());
            vo = repository.save(vo);
            return to;
        }catch (DataIntegrityViolationException ex){
            throw new BusinessException(422, null);
        }

    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CardBalanceTO getCardBalance(String number) {
        CardVO vo = repository.findByNumber(number);
        try{
            return new CardBalanceTO(vo.getBalance());
        }catch (NullPointerException ex){
            throw new BusinessException(404, null);
        }catch (Exception ex){
            throw new BusinessException(404, null);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TransactionResultTO performTransaction(TransactionTO transaction) {
        try{
            CardVO vo = repository.findByNumberWithLock(transaction.getNumber());
            //Nao encontrei um jeito de verificar a senha sem utilizar o if. Poderia fazer isso via banco de dados, pegando o cartão pelo numero e senha,
            // mas dai não teria como eu verificar se o cartao existe ou se eh a senha que esta errada.
            if(vo.getPassword().equals(transaction.getPassword())){
                return new TransactionResultTO(422, TransactionStatus.INVALID_PASSWORD);
            }
            vo.setBalance(vo.getBalance().subtract(transaction.getValue()));
            return vo.getBalance().doubleValue() < 0
                    ? new TransactionResultTO(422, TransactionStatus.INSUFFICIENT_FUNDS)
                    : new TransactionResultTO(201, TransactionStatus.OK);
        }catch (NullPointerException ex){
            return new TransactionResultTO(422, TransactionStatus.NONEXISTENT_CARD);
        }
    }
}
