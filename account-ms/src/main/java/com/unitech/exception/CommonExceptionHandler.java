package com.unitech.exception;

import com.unitech.domain.model.CustomFieldErrorResponse;
import com.unitech.domain.model.ErrorResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Exception occurred : {}", ex.getMessage());
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

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(404, e.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(404, e.getMessage()));
    }

    @ExceptionHandler(InSufficientFundException.class)
    public ResponseEntity<?> handleInsufficientFundException(InSufficientFundException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(400, e.getMessage()));
    }

    @ExceptionHandler(InValidTransferException.class)
    public ResponseEntity<?> handleBadTransactionException(InValidTransferException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(400, e.getMessage()));
    }

    @ExceptionHandler(InActiveAccountException.class)
    public ResponseEntity<?> handleInActiveAccountException(InActiveAccountException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(400, e.getMessage()));
    }

    @ExceptionHandler(InValidSenderAccountException.class)
    public ResponseEntity<?> handleBadTransactionException(InValidSenderAccountException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(400, e.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleBadTransactionException(InvalidTokenException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(401, e.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleBadTransactionException(FeignException e) {
        log.error("Exception occurred : {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.instance(401, e.getMessage()));
    }

}
