package com.example.authorization_service.dto;

import com.example.authorization_service.domain.Role;
import lombok.Data;

@Data
public class RegistrationDto {
    private String nickname;
    private String login;
    private String password;
    private Role role;

}
