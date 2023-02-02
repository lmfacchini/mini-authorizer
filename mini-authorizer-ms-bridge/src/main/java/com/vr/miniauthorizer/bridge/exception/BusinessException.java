package com.vr.miniauthorizer.bridge.exception;

public class BusinessException extends RuntimeException{

    public final int CODE;

    public final String MESSAGE;

    public BusinessException(int CODE, String MESSAGE) {
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }
}
