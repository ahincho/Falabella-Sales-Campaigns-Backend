package com.falabella.sales.campaigns.domain.exceptions;

public class CouponNotFoundException extends Exception {
    public CouponNotFoundException(String message) {
        super(message);
    }
}
