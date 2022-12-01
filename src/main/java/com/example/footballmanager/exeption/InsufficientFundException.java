package com.example.footballmanager.exeption;

public class InsufficientFundException extends BadPlayerTransferException {

    public InsufficientFundException(String message) {
        super(message);
    }
}
