package com.example.orders_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper()
                .writeValueAsString(new MyErrorResponse(
                        "about:blank",
                        "Unauthorized",
                        401,
                        ex.getMessage(),
                        request.getRequestURI())));
    }

    @AllArgsConstructor
    @Getter
    private static class MyErrorResponse {
        private String type;

        private String title;

        private int status;

        private String detail;

        private String instance;
    }
}

