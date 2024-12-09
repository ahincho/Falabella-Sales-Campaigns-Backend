package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.Email;
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
public class UserUpdateRequest {
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
    @Email(message = "You must provide a valid email address")
    private String email;
}
