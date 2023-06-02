package com.example.authorization_service.dto;

import com.example.authorization_service.domain.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoDto {
    String nickname;

    String login;

    Role role;

    LocalDateTime createdAt;
}
