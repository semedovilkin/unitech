package com.unitech.exception;

public class InSufficientFundException extends RuntimeException {

    public static final String MESSAGE = "You don't have enough money in this account.";

    public InSufficientFundException() {
        super(MESSAGE);

    }
}
