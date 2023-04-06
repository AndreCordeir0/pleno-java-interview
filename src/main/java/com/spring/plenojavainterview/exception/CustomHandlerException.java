package com.spring.plenojavainterview.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleErrors(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // status padrão

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status.value());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }
}