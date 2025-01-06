package com.falabella.sales.campaigns.application.services;

import com.falabella.sales.campaigns.application.ports.in.FindCampaignsServicePort;
import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.commons.domain.models.PaginationResult;

import org.springframework.stereotype.Service;

@Service
public class FindCampaignsService implements FindCampaignsServicePort {
    private final CampaignPersistencePort campaignPersistencePort;
    public FindCampaignsService(CampaignPersistencePort campaignPersistencePort) {
        this.campaignPersistencePort = campaignPersistencePort;
    }
    @Override
    public PaginationResult<Campaign> execute(CampaignFilters campaignFilters) {
        return this.campaignPersistencePort.findCampaigns(campaignFilters);
    }
}
