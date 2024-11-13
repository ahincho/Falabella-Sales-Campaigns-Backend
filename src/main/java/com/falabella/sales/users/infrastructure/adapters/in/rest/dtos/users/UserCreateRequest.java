package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.users;

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
public class UserCreateRequest {
    @NotBlank(message = "")
    @Size(min = 2, max = 32, message = "")
    @Pattern(
        regexp = "^[a-zA-Z\\s]*$",
        message = "Firstname must contain only letters and spaces"
    )
    private String firstname;
    @NotBlank(message = "")
    @Size(min = 2, max = 32, message = "")
    @Pattern(
        regexp = "^[a-zA-Z\\s]*$",
        message = "Lastname must contain only letters and spaces"
    )
    private String lastname;
    @NotBlank(message = "")
    @Size(min = 2, max = 32, message = "")
    @Pattern(
        regexp = "^[a-zA-Z0-9]*$",
        message = "Username must contain only alphanumeric characters without spaces"
    )
    private String username;
    @NotBlank(message = "")
    @Size(max = 64, message = "Email must not exceed 64 characters")
    @Email(message = "")
    private String email;
}
