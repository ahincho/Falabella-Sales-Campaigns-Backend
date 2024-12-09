package com.falabella.sales.commons.infrastructure.adapters.in.rest.utils;

public class IntegerUtils {
    public static Integer safeParseInteger(String value, Integer defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
