package com.example.authorization_service.service.implementation;

import com.example.authorization_service.domain.AuthException;
import com.example.authorization_service.domain.Role;
import com.example.authorization_service.domain.UserInfo;
import com.example.authorization_service.entity.User;
import com.example.authorization_service.service.abstraction.TokenProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider implements TokenProvider {
    private final SecretKey secretKey;

    public JwtProvider(@Value("${my.security.secret}") String secret) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String generateAccessToken(User user, Date accessExpiration) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(accessExpiration)
                .signWith(secretKey)
                .claim("role", user.getRole().name())
                .claim("nickname", user.getUsername())
                .claim("user_id", user.getId())
                .compact();
    }

    @Override
    public UserInfo getUserInfo(String token) {
        try {
            Claims claims =
                    Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return new UserInfo(
                    claims.getSubject(),
                    Role.valueOf(claims.get("role", String.class)),
                    claims.get("nickname", String.class),
                    claims.get("user_id", Long.class));
        } catch (ExpiredJwtException e) {
            throw new AuthException("Срок действия токена истек");
        } catch (UnsupportedJwtException |
                MalformedJwtException |
                SignatureException | // и что мне делать? Написано, что ошибка устаревшая, но из сигнатуры метода ее не убрали
                IllegalArgumentException e) {
            throw new AuthException(e.getMessage());
        }
    }
}
