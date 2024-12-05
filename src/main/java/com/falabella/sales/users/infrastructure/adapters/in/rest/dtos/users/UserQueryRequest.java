package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.users;

import com.falabella.sales.users.infrastructure.adapters.in.rest.annotations.ValidPositiveInteger;

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
public class UserQueryRequest {
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank
    @ValidPositiveInteger
    private String size = "10";
    @Size(max = 32, message = "Firstname must be at most 32 characters")
    @Pattern(
        regexp = "^[a-zA-Z\\s]*$",
        message = "Firstname must contain only letters and spaces"
    )
    private String firstname;
    @Size(max = 32, message = "Lastname must be at most 32 characters")
    @Pattern(
        regexp = "^[a-zA-Z\\s]*$",
        message = "Lastname must contain only letters and spaces"
    )
    private String lastname;
    @Size(max = 32, message = "Username must be at most 32 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]*$",
        message = "Username must contain only alphanumeric characters without spaces"
    )
    private String username;
    @Size(max = 64, message = "Email must not exceed 64 characters")
    private String email;
    @Pattern(regexp = "^(true|false)$", message = "Include roles must be a string with 'true' or 'false' as the value")
    private String includeRoles;
}
