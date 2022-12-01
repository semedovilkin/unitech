package com.example.accountms.exception;

public class InvalidTokenException extends RuntimeException {
    private String msg;

    public InvalidTokenException(String msg) {
        this.msg = msg;
    }
}
