package com.example.authorization_service.service.implementation;

import com.example.authorization_service.dto.LoginDto;
import com.example.authorization_service.entity.User;
import com.example.authorization_service.service.abstraction.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenProviderImpl implements TokenProvider {
    private final SecretKey secretKey;
    private final long accessExpirationMinutes;

    public TokenProviderImpl(@Value("${my.security.secret}") String secret,
                             @Value("${my.security.access-expiration-minutes}") long accessExpirationMinutes) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessExpirationMinutes = accessExpirationMinutes;
    }

    @Override
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(getExpiration())
                .signWith(secretKey)
                .claim("role", user.getRole())
                .claim("nickname", user.getUsername())
                .compact();
    }

    private Date getExpiration() {
        return Date.from(
                LocalDateTime.now().plusMinutes(accessExpirationMinutes)
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
