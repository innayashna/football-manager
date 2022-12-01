package com.example.footballmanager.exeption;

public class UnsupportedTransferException extends BadPlayerTransferException {

    public UnsupportedTransferException(String message) {
        super(message);
    }
}
