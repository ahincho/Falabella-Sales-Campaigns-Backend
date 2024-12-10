package com.falabella.sales.campaigns.application.services;

import com.falabella.sales.campaigns.application.ports.in.CreateCampaignUseCase;
import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.models.Campaign;

import org.springframework.stereotype.Service;

@Service
public class CreateCampaignService implements CreateCampaignUseCase {

    private final CampaignPersistencePort campaignPersistencePort;

    public CreateCampaignService(CampaignPersistencePort campaignPersistencePort) {
        this.campaignPersistencePort = campaignPersistencePort;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        return campaignPersistencePort.saveCampaign(campaign);
    }
}