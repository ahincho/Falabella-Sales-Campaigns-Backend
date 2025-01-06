package com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 64, message = "Title must be between 2 and 64 characters")
    private String title;
    @NotBlank(message = "Message is required")
    @Size(min = 2, max = 256, message = "Message must be between 2 and 256 characters")
    private String message;
    @NotBlank(message = "Status is required")
    @Size(min = 2, max = 32, message = "Status must be between 2 and 32 characters")
    private String status;
    private Long userID;
}

