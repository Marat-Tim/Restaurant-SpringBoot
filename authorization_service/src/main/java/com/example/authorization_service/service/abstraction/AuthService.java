package com.example.authorization_service.service.abstraction;

import com.example.authorization_service.dto.JwtResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;

public interface AuthService {
    void registerNewUser(RegistrationDto registrationDto);

    JwtResponseDto loginUser(LoginDto loginDto);

    UserInfoDto getInfoByToken(String accessToken);
}
