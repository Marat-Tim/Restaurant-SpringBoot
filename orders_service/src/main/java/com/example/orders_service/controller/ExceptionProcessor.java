package com.example.orders_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionProcessor {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleIncorrectFormatException(HttpMessageNotReadableException ex) {
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, "Неправильный формат входных данных");
    }
}
