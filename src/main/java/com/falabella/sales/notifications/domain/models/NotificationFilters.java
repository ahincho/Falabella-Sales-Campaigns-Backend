package com.falabella.sales.notifications.domain.models;

import com.falabella.sales.commons.domain.models.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationFilters {
    private Page page;
    private String title;
    private String message;
    private String status;
    private Long userID;
}
