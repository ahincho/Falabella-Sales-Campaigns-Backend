package com.falabella.sales.campaigns.infrastructure.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String loginMessage;
    private String loginImage;
    private String backgroundColor;
    private String homeImage;
    private String homeMessage;
    private String homeMessageImage;
    private String messageTextColor;
    private String couponUsageMessage;
    private String legalConditions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
