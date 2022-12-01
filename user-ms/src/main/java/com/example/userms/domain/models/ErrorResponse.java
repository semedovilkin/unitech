package com.example.userms.domain.models;

public class ErrorResponse {

    private Integer code;
    private String message;


    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ErrorResponse instance(Integer code, String message) {
        return new ErrorResponse(code, message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

