package com.falabella.sales.files.infrastructure.in.rest.advices;

import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.ExceptionResponse;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.exceptions.FileNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileRestAdvice {
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionResponse> fileNotFound(
        FileNotFoundException fileNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusDescription(HttpStatus.NOT_FOUND.name())
            .message(fileNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(FileDuplicatedException.class)
    public ResponseEntity<ExceptionResponse> fileDuplicated(
        FileDuplicatedException fileDuplicatedException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.CONFLICT.value())
            .statusDescription(HttpStatus.CONFLICT.name())
            .message(fileDuplicatedException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
}
