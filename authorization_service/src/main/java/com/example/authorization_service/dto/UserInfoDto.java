package com.example.authorization_service.dto;

import com.example.authorization_service.domain.Role;
import lombok.Data;

@Data
public class UserInfoDto {
    String nickname;
    String login;
    Role role;
}
