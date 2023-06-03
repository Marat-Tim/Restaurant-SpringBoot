package com.example.authorization_service.service.abstraction;

import com.example.authorization_service.domain.UserInfo;
import com.example.authorization_service.entity.User;

import java.util.Date;

public interface TokenProvider {
    String generateAccessToken(User user, Date accessExpiration);

    UserInfo getUserInfo(String token);
}
