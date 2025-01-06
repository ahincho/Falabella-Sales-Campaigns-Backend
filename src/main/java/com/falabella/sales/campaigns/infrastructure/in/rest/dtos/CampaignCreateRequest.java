package com.falabella.sales.campaigns.infrastructure.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignCreateRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull(message = "Start date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @NotNull(message = "End date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;
    private String loginMessage;
    private MultipartFile loginImage;
    private String backgroundColor;
    private MultipartFile homeImage;
    private String homeMessage;
    private MultipartFile homeMessageImage;
    private String messageTextColor;
    private String couponUsageMessage;
    private String legalConditions;
}
