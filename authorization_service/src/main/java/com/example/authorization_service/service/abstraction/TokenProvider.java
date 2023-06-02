package com.example.authorization_service.service.abstraction;

import com.example.authorization_service.entity.User;
import io.jsonwebtoken.Claims;

import java.util.Date;

public interface TokenProvider {
    String generateAccessToken(User user, Date accessExpiration);

    Claims getClaims(String token);
}
