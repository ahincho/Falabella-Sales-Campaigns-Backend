package com.falabella.sales.commons.infrastructure.adapters.in.rest.advices;

import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(request.getRequestURI())
            .method(request.getMethod())
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .statusDescription(HttpStatus.UNAUTHORIZED.name())
            .message(authenticationException.getMessage())
            .build();
        String jsonResponse = objectMapper.writeValueAsString(exceptionResponse);
        response.getWriter().write(jsonResponse);
    }
}
