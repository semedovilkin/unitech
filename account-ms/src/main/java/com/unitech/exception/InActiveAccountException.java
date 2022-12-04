package com.unitech.exception;

public class InActiveAccountException extends RuntimeException{

    public static final String MESSAGE = "Account is inactive: %s";

    public InActiveAccountException(Integer id) {
        super(String.format(MESSAGE, id));
    }

}
