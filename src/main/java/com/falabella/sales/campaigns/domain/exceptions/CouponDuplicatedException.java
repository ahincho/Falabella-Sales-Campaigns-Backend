package com.falabella.sales.campaigns.domain.exceptions;

public class CouponDuplicatedException extends Exception {
    public CouponDuplicatedException(String message) {
        super(message);
    }
}
