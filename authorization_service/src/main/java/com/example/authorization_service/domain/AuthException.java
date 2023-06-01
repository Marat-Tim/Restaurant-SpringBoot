package com.example.authorization_service.domain;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
