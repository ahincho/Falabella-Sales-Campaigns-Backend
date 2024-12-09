package com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos;

import org.springframework.validation.FieldError;

public record NotValidField(
    String field,
    String message
) {
    public NotValidField(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
