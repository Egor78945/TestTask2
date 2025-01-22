package com.example.library.controller.book.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = BookControllerExceptionHandler.class)
public class BookControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandling(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
