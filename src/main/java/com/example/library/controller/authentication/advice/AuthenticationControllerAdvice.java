package com.example.library.controller.authentication.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = AuthenticationControllerExceptionHandler.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandling(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
