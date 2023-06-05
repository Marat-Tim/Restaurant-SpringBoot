package com.example.authorization_service;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.dto.RegistrationDto;
import com.example.authorization_service.dto.TokenResponseDto;
import com.example.authorization_service.dto.UserInfoDto;
import com.example.authorization_service.service.abstraction.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public TokenResponseDto register(@Valid RegistrationDto registrationDto) {
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
    public ErrorResponse handleAuthException(AuthException e) {
        return ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ErrorResponse handleMappingException(HttpMessageNotReadableException e){
        return ErrorResponse.create(e, HttpStatus.BAD_REQUEST, "Некорректный формат входных данных");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, String.join("|", errorMessages));
    }
}
