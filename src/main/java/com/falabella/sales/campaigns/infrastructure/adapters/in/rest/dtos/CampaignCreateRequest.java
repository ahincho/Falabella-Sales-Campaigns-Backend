package com.falabella.sales.campaigns.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignCreateRequest {

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

    private String loginMessage;

    private MultipartFile loginImage;

    private String backgroundColor;

    private MultipartFile homeImage;

    private String homeMessage;

    private String messageTextColor;

    private String couponUsageMessage;

    private String legalConditions;

    private List<CouponCampaign> couponsList;
}