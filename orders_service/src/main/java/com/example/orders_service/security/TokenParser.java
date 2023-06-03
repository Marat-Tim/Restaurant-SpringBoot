package com.example.orders_service.security;

import org.springframework.security.core.Authentication;

public interface TokenParser {
    Authentication parseToken(String accessToken);
}
