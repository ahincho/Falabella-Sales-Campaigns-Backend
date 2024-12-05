package com.falabella.sales.users.infrastructure.adapters.in.rest.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveIntegerValidator implements ConstraintValidator<ValidPositiveInteger, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
