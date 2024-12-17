package com.falabella.sales.campaigns.application.ports.out;

import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.commons.domain.models.PaginationResult;

import java.util.Optional;

public interface CampaignPersistencePort {
    Campaign createOneCampaign(Campaign campaign);
    PaginationResult<Campaign> findCampaigns(CampaignFilters campaignFilters);
    Optional<Campaign> findOneCampaignById(Integer campaignId);
    Boolean existsOneCampaignById(Integer campaignId);
    void updateOneCampaign(Integer campaignId, Campaign campaign);
    void deleteOneCampaign(Integer campaignId);
}
