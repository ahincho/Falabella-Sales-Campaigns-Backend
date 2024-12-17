package com.falabella.sales.campaigns.domain.models;

import lombok.Getter;

@Getter
public enum CouponStatus {
    ACTIVE("ACTIVE", "Active");
    private final String code;
    private final String description;
    CouponStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
