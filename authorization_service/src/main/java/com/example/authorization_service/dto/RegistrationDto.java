package com.example.authorization_service.dto;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.domain.Role;

public class RegistrationDto {
    private String nickname;

    private String login;

    private String password;

    private Role role;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (!login.contains("@")) {
            throw new AuthException("Некорректный логин");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
