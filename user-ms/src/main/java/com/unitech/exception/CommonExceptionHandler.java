package com.unitech.exception;

import com.unitech.domain.dto.response.CustomFieldErrorResponse;
import com.unitech.domain.models.CustomUserException;
import com.unitech.domain.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> authenticationExceptionHandler(Exception e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.instance(401, "You dont have enough access for use this resource"));
    }


    @ExceptionHandler(CustomUserException.class)
    public ResponseEntity<?> customUserExceptionHandler(CustomUserException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.instance(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> commonExceptionHandler(IllegalArgumentException ex) {
        ex.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.instance(400, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.instance(500, "Internal error occurred"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<CustomFieldErrorResponse> customerFieldErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(item -> {
            customerFieldErrors.add(
                    new CustomFieldErrorResponse()
                            .setField(item.getField())
                            .setMessage(item.getDefaultMessage()));
        });
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(400, "Argument not valid", customerFieldErrors));
    }
}
