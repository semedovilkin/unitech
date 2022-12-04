package com.unitech.exception;

public class InValidTransferException extends RuntimeException{

    public static final String MESSAGE = "It does not allow transfer to the same account";

    public InValidTransferException() {
        super(MESSAGE);
    }
}
