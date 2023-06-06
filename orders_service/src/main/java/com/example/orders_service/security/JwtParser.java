package com.example.orders_service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtParser implements TokenParser {
    private final SecretKey secretKey;

    public JwtParser(@Value("${my.security.secret}") String secret) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public Authentication parseToken(String accessToken) {
        return convertClaimsToAuthentication(getClaims(accessToken));
    }

    private static Authentication convertClaimsToAuthentication(Claims claims) {
        JwtAuthentication authentication = new JwtAuthentication();
        authentication.setRole(Role.valueOf(claims.get("role", String.class)));
        authentication.setNickname(claims.get("nickname", String.class));
        authentication.setLogin(claims.getSubject());
        authentication.setUserId(claims.get("user_id", Long.class));
        return authentication;
    }

    public Claims getClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Срок действия токена истек");
        } catch (UnsupportedJwtException |
                 MalformedJwtException |
                 SignatureException |
                 // и что мне делать? Написано, что ошибка устаревшая, но из сигнатуры метода ее не убрали
                 IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
