package com.falabella.sales.campaigns.application.ports.out;

import com.falabella.sales.campaigns.domain.models.Campaign;

public interface CampaignPersistencePort {
    Campaign saveCampaign(Campaign campaign);
}