package com.falabella.sales.commons.infrastructure.adapters.in.rest.advices;

import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(request.getRequestURI())
            .method(request.getMethod())
            .statusCode(HttpStatus.FORBIDDEN.value())
            .statusDescription(HttpStatus.FORBIDDEN.name())
            .message(accessDeniedException.getMessage())
            .build();
        String jsonResponse = this.objectMapper.writeValueAsString(exceptionResponse);
        response.getWriter().write(jsonResponse);
    }
}
