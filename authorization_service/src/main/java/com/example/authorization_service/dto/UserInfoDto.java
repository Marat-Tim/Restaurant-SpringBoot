package com.example.authorization_service.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    String nickname;
    String login;
    Role role;
}
