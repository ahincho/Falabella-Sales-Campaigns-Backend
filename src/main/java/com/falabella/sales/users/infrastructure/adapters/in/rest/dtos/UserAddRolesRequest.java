package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddRolesRequest {
    @NotNull(message = "Role ids list cannot be null")
    @NotEmpty(message = "Role ids list cannot be empty")
    @Size(max = 25, message = "Role ids length cannot exceed 25")
    private List<@Min(value = 1, message = "Role id must be greater than 0") Integer> roleIds;
}
