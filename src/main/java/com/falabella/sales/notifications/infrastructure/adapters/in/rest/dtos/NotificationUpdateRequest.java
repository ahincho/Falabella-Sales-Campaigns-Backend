package com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationUpdateRequest {
    @Size(max = 32, message = "Title must be at most 32 characters")
    private String title;
    @Size(max = 256, message = "Message must be at most 256 characters")
    private String message;
    @Size(max = 32, message = "Status must be at most 32 characters")
    private String status;
    private Long userID;
}
