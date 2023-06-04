package com.example.authorization_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfo {
    private String login;

    private Role role;

    private String nickname;

    private long userId;
}
