package com.example.authorization_service;

import com.example.authorization_service.dto.JwtResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;
import org.springframework.web.bind.annotation.*;

public interface AuthApi {
    @PostMapping("/register")
    JwtResponseDto register(@RequestBody RegistrationDto registrationDto);

    @PostMapping("/login")
    JwtResponseDto login(@RequestBody LoginDto registrationDto);

    @GetMapping("/info")
    UserInfoDto info(@RequestBody String token);
}
