package com.example.orders_service.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    customer,
    chef,
    manager;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
