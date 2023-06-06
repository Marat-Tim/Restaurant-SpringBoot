package com.example.orders_service.domain;

public class DishException extends RuntimeException {
    public DishException(String message) {
        super(message);
    }

    public static DishException dishNotFound() {
        return new DishException("Блюда с таким id не существует");
    }
}
