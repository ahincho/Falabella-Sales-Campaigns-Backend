package com.falabella.sales.campaigns.application.ports.in;

import com.falabella.sales.campaigns.domain.exceptions.CampaignNotFoundException;
import com.falabella.sales.campaigns.domain.models.Campaign;

public interface FindOneCampaignByIdServicePort {
    Campaign execute(Integer campaignId) throws CampaignNotFoundException;
}
