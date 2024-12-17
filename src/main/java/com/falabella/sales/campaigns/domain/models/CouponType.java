package com.falabella.sales.campaigns.domain.models;

import lombok.Getter;

@Getter
public enum CouponType {
    OMNI("OMNI", "OMNI TYPE");
    private final String code;
    private final String description;
    CouponType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
