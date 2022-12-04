package com.unitech.domain.models;


import com.unitech.domain.dto.response.CustomFieldErrorResponse;
import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private Integer code;
    private String message;
    private List<CustomFieldErrorResponse> fields;


    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(Integer code, String message, List<CustomFieldErrorResponse> fields) {
        this.code = code;
        this.message = message;
        this.fields = fields;
    }

    public static ErrorResponse instance(Integer code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse instance(Integer code, String message, List<CustomFieldErrorResponse> fields) {
        return new ErrorResponse(code, message, fields);
    }

}


