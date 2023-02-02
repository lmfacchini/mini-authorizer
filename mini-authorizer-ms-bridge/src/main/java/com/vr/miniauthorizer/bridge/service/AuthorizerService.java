package com.vr.miniauthorizer.bridge.service;

import com.vr.miniauthorizer.bridge.to.CardBalanceTO;
import com.vr.miniauthorizer.bridge.to.CardTO;
import com.vr.miniauthorizer.bridge.to.TransactionResultTO;
import com.vr.miniauthorizer.bridge.to.TransactionTO;

public interface AuthorizerService {
    CardTO createCard(CardTO to);

    CardBalanceTO getCardBalance(String number);

    TransactionResultTO performTransaction(TransactionTO transaction);
}
