package com.falabella.sales.campaigns.application.services;

import com.falabella.sales.campaigns.application.ports.in.FindOneCampaignByIdServicePort;
import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.exceptions.CampaignNotFoundException;
import com.falabella.sales.campaigns.domain.models.Campaign;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneCampaignByIdService implements FindOneCampaignByIdServicePort {
    private final CampaignPersistencePort campaignPersistencePort;
    public FindOneCampaignByIdService(CampaignPersistencePort campaignPersistencePort) {
        this.campaignPersistencePort = campaignPersistencePort;
    }
    @Override
    public Campaign execute(Integer campaignId) throws CampaignNotFoundException {
        Optional<Campaign> campaign = this.campaignPersistencePort.findOneCampaignById(campaignId);
        if (campaign.isEmpty()) {
            throw new CampaignNotFoundException("Campaign with id '" + campaignId + "' not found");
        }
        return campaign.get();
    }
}
