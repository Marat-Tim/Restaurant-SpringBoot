package com.example.authorization_service;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.UserInfoDto;
import com.example.authorization_service.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public TokenResponseDto register(RegistrationDto registrationDto) {
        authService.registerNewUser(registrationDto);
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin(registrationDto.getLogin());
        loginDto.setPassword(registrationDto.getPassword());
        return login(loginDto);
    }

    @Override
    public TokenResponseDto login(LoginDto loginDto) {
        return authService.loginUser(loginDto);
    }

    @Override
    public UserInfoDto info(String accessToken) {
        return authService.getInfoByToken(accessToken);
    }

    @ExceptionHandler(AuthException.class)
    public ErrorResponse handleException(AuthException e) {
        return ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
