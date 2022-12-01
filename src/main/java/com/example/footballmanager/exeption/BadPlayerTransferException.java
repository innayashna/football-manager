package com.example.footballmanager.exeption;

public class BadPlayerTransferException extends RuntimeException {

    public BadPlayerTransferException(String message) {
        super(message);
    }
}
