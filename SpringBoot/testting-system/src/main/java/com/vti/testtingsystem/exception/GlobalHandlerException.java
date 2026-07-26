package com.vti.testtingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException extends RuntimeException {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BusinessException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "500");
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    ;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "500");
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    ;
}
