package com.unitech.exception;

public class InvalidTokenException extends RuntimeException {
    private String msg;

    public InvalidTokenException(String msg) {
        this.msg = msg;
    }
}
