package com.falabella.sales.notifications.infrastructure.adapters.in.rest.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PageSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageSize {
    String message() default "Page size must be a numeric value between 1 and 100";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
