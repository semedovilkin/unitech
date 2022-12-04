package com.unitech.exception;

public class CustomerNotFoundException extends RuntimeException{

    public static final String MESSAGE = "Customer not found: %s";

    public CustomerNotFoundException(Integer id) {
        super(String.format(MESSAGE, id));
    }

    public CustomerNotFoundException(String pin) {
        super(String.format(MESSAGE, pin));
    }

}
