package com.example.authorization_service.dto;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.domain.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class RegistrationDto {
    private String nickname;

    @Email(message = "Логин должен быть в формате электронной почты")
    private String login;

    private String password;

    private Role role;
}
