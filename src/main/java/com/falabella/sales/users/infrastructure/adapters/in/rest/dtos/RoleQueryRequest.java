package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos;

import com.falabella.sales.users.infrastructure.adapters.in.rest.annotations.ValidPositiveInteger;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryRequest {
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank
    @ValidPositiveInteger
    @Max(value = 100, message = "Page size must not exceed 100")
    private String size = "10";
    @Size(max = 32, message = "Name must be at most 32 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]*$",
        message = "Name must contain only alphanumeric characters without spaces"
    )
    private String name;
}
