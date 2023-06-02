package com.example.authorization_service.service.implementation;

import com.example.authorization_service.domain.AuthException;
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
                .claim("role", user.getRole())
                .claim("nickname", user.getUsername())
                .claim("user_id", user.getId())
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
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
