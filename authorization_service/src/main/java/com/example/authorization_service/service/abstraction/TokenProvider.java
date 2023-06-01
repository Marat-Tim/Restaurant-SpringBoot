package com.example.authorization_service.service.abstraction;

import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.entity.User;

public interface TokenProvider {
    String generateAccessToken(User user);
}
