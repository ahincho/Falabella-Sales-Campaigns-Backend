package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.commons;

import org.springframework.validation.FieldError;

public record NotValidField(
    String field,
    String message
) {
    public NotValidField(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
