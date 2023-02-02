package com.vr.miniauthorizer.ws.controller;

import com.vr.miniauthorizer.bridge.constants.TransactionStatus;
import com.vr.miniauthorizer.bridge.controller.AuthorizerController;
import com.vr.miniauthorizer.bridge.exception.BusinessException;
import com.vr.miniauthorizer.bridge.service.AuthorizerService;
import com.vr.miniauthorizer.bridge.to.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorizerControllerImpl implements AuthorizerController {

    private AuthorizerService service;

    public AuthorizerControllerImpl(AuthorizerService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CardTO> card(CardTO to) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createCard(to));
        }catch (BusinessException ex){
            return ResponseEntity.status(ex.CODE).body(to);
        }
    }

    @Override
    public ResponseEntity<CardBalanceTO> balance(String number) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.getCardBalance(number));
        }catch (BusinessException ex){
            return ResponseEntity.status(ex.CODE).build();
        }
    }

    @Override
    public ResponseEntity<TransactionStatus> transaction(TransactionTO transaction) {
        TransactionResultTO transactionResult = service.performTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResult.getStatus());
    }

}
