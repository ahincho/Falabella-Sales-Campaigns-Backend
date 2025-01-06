package com.falabella.sales.campaigns.domain.exceptions;

public class CampaignDuplicatedException extends Exception {
    public CampaignDuplicatedException(String message) {
        super(message);
    }
}
