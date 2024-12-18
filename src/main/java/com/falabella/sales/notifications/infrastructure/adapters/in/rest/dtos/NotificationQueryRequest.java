package com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos;

import com.falabella.sales.notifications.infrastructure.adapters.in.rest.annotations.ValidPositiveInteger;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationQueryRequest {
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank
    @ValidPositiveInteger
    @Max(value = 25, message = "Page size must not exceed 25")
    private String size = "10";
    private Long id;
    private String title;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userID;
}

