package com.falabella.sales.campaigns.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponCampaign {
    private String id;
    private String campaignId;
    private String couponCode;
    private Boolean isUsed;
    private String usedAt;
}