package com.rewards.customer.exception;

public class InvalidTransactionException extends RuntimeException {

    public InvalidTransactionException(String msg) {
        super(msg);
    }
}