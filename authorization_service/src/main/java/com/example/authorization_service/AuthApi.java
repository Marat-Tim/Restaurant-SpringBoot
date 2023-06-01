package com.example.authorization_service;

import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;
import org.springframework.web.bind.annotation.*;

public interface AuthApi {
    @PostMapping("/register")
    TokenResponseDto register(@RequestBody RegistrationDto registrationDto);

    @PostMapping("/login")
    TokenResponseDto login(@RequestBody LoginDto loginDto);

    @GetMapping("/info")
    UserInfoDto info(@RequestBody String accessToken);
}
