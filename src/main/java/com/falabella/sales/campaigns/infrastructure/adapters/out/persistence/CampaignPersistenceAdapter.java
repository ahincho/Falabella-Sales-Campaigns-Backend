package com.falabella.sales.campaigns.infrastructure.adapters.out.persistence;

import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.entities.CampaignJpaEntity;
import com.falabella.sales.campaigns.infrastructure.adapters.out.persistence.repositories.CampaignRepository;

import org.springframework.stereotype.Component;

@Component
public class CampaignPersistenceAdapter implements CampaignPersistencePort {

    private final CampaignRepository campaignRepository;

    public CampaignPersistenceAdapter(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign saveCampaign(Campaign campaign) {
        CampaignJpaEntity campaignJpaEntity = new CampaignJpaEntity();
        // Mapear los campos de Campaign a CampaignJpaEntity
        campaignJpaEntity.setName(campaign.getName());
        campaignJpaEntity.setStartDate(campaign.getStartDate());
        campaignJpaEntity.setEndDate(campaign.getEndDate());
        campaignJpaEntity.setTotalCoupons(campaign.getTotalCoupons());
        campaignJpaEntity.setUsedCouponsCount(campaign.getUsedCouponsCount());
        campaignJpaEntity.setStatus(campaign.getStatus());
        campaignJpaEntity.setLoginMessage(campaign.getLoginMessage());
        campaignJpaEntity.setLoginImage(campaign.getLoginImage());
        campaignJpaEntity.setBackgroundColor(campaign.getBackgroundColor());
        campaignJpaEntity.setHomeMessageImage(campaign.getHomeMessageImage());
        campaignJpaEntity.setHomeImage(campaign.getHomeImage());
        campaignJpaEntity.setHomeMessage(campaign.getHomeMessage());
        campaignJpaEntity.setMessageTextColor(campaign.getMessageTextColor());
        campaignJpaEntity.setCouponUsageMessage(campaign.getCouponUsageMessage());
        campaignJpaEntity.setLegalConditions(campaign.getLegalConditions());
        // Guardar la entidad en la base de datos
        CampaignJpaEntity savedEntity = campaignRepository.save(campaignJpaEntity);
        // Mapear los campos de CampaignJpaEntity a Campaign
        campaign.setId(savedEntity.getId().toString());
        return campaign;
    }
}