package com.falabella.sales.campaigns.application.ports.in;

import com.falabella.sales.campaigns.domain.models.Campaign;

public interface CreateCampaignUseCase {
    Campaign createCampaign(Campaign campaign);
}