package com.example.orders_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    CUSTOMER("customer"),
    CHEF("chef"),
    MANAGER("manager");

    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
