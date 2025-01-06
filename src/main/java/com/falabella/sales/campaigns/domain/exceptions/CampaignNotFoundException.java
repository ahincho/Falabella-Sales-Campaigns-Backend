package com.falabella.sales.campaigns.domain.exceptions;

public class CampaignNotFoundException extends Exception {
    public CampaignNotFoundException(String message) {
        super(message);
    }
}
