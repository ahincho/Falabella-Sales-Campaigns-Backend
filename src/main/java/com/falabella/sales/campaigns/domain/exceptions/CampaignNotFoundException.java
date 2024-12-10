package com.falabella.sales.campaigns.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CampaignNotFoundException extends RuntimeException {
    private final String message;
}