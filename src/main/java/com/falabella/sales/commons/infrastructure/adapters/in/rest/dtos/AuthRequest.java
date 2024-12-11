package com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
