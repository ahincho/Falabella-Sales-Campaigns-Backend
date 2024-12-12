package com.falabella.sales.commons.infrastructure.adapters.in.rest.advices;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtRestAdvice {
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ExceptionResponse> tokenExpiredException(
        TokenExpiredException tokenExpiredException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .message(tokenExpiredException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionResponse> jwtVerificationException(
        JWTVerificationException jwtVerificationException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .statusDescription(HttpStatus.UNAUTHORIZED.name())
            .message(jwtVerificationException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }
}
