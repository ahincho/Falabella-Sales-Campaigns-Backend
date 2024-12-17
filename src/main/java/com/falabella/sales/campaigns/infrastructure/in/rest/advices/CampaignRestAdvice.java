package com.falabella.sales.campaigns.infrastructure.in.rest.advices;

import com.falabella.sales.campaigns.domain.exceptions.CampaignDuplicatedException;
import com.falabella.sales.campaigns.domain.exceptions.CampaignNotFoundException;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CampaignRestAdvice {
    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<ExceptionResponse> campaignNotFound(
        CampaignNotFoundException campaignNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusDescription(HttpStatus.NOT_FOUND.name())
            .message(campaignNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(CampaignDuplicatedException.class)
    public ResponseEntity<ExceptionResponse> campaignDuplicated(
        CampaignDuplicatedException campaignDuplicatedException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.CONFLICT.value())
            .statusDescription(HttpStatus.CONFLICT.name())
            .message(campaignDuplicatedException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
}
