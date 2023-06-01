package com.example.authorization_service.service.abstraction;

import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;

public interface AuthService {
    void registerNewUser(RegistrationDto registrationDto);

    TokenResponseDto loginUser(LoginDto loginDto);

    UserInfoDto getInfoByToken(String accessToken);
}
