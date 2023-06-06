package com.example.orders_service.domain;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
