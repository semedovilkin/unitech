package com.unitech.exception;

public class AccountNotFoundException extends RuntimeException{

    public static final String MESSAGE = "Account not found: %s";

    public AccountNotFoundException(Integer id) {
        super(String.format(MESSAGE, id));
    }
}
