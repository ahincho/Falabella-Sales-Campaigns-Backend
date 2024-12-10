package com.falabella.sales.campaigns.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    @NotBlank
    private String totalCoupons;

    @NotBlank
    private String usedCouponsCount;

    @NotNull
    private Boolean status;

    private String createdAt;

    private String loginMessage;

    private String loginImage;

    private String backgroundColor;

    private String homeMessageImage;

    private String homeImage;

    private String homeMessage;

    private String messageTextColor;

    private String couponUsageMessage;

    private String legalConditions;

    private List<CouponCampaign> couponsList;
}