package com.example.orders_service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    private static final String BEARER = "Bearer ";

    private static final String AUTHORIZATION = "Authorization";

    private final SecretKey secretKey;

    public JwtFilter(@Value("${my.security.secret}") String secret) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        var token = getTokenFromRequest((HttpServletRequest) request);
        Claims claims = getClaims(token);
        Authentication authentication = convertClaimsToAuthentication(claims);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final var bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(BEARER)) {
            return bearer.substring(BEARER.length());
        }
        throw new BadCredentialsException("Нет заголовка \"Bearer\" в запросе");
    }

    private static Authentication convertClaimsToAuthentication(Claims claims) {
        JwtAuthentication authentication = new JwtAuthentication();
        authentication.setRole(claims.get("role", Role.class));
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
                 SignatureException | // и что мне делать? Написано, что ошибка устаревшая, но из сигнатуры метода ее не убрали
                 IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
