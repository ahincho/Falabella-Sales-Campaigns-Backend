package com.falabella.sales.campaigns.infrastructure.in.rest.dtos;

import com.falabella.sales.users.infrastructure.adapters.in.rest.annotations.ValidPositiveInteger;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignQueryRequest {
    @NotBlank
    @ValidPositiveInteger
    private String page = "0";
    @NotBlank
    @ValidPositiveInteger
    @Max(value = 25, message = "Page size must not exceed 25")
    private String size = "10";
}
