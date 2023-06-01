package com.example.authorization_service.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String nickname;
    private String login;
    private String password;
    private Role role;

}
