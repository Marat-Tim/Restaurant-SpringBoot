package com.example.authorization_service;

import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface AuthApi {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    TokenResponseDto register(@RequestBody RegistrationDto registrationDto);

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    TokenResponseDto login(@RequestBody LoginDto loginDto);

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    UserInfoDto info(@RequestParam String accessToken);
}
