package com.falabella.sales.campaigns.infrastructure.out.persistence.implementations;

import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.campaigns.infrastructure.out.persistence.entities.CampaignEntity;
import com.falabella.sales.campaigns.infrastructure.out.persistence.mappers.CampaignPersistenceMapper;
import com.falabella.sales.campaigns.infrastructure.out.persistence.repositories.CampaignJpaRepository;
import com.falabella.sales.commons.domain.models.PaginationResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CampaignPostgresPersistenceAdapter implements CampaignPersistencePort {
    private final CampaignJpaRepository campaignJpaRepository;
    public CampaignPostgresPersistenceAdapter(CampaignJpaRepository campaignJpaRepository) {
        this.campaignJpaRepository = campaignJpaRepository;
    }
    @Override
    @Transactional
    public Campaign createOneCampaign(Campaign campaign) {
        CampaignEntity savedCampaign = this.campaignJpaRepository.save(CampaignPersistenceMapper.domainToEntity(campaign));
        return CampaignPersistenceMapper.entityToDomain(savedCampaign);
    }
    @Override
    public PaginationResult<Campaign> findCampaigns(CampaignFilters campaignFilters) {
        Pageable pageable = buildPageable(campaignFilters);
        Page<CampaignEntity> campaignEntities = this.campaignJpaRepository.findAll(pageable);
        return CampaignPersistenceMapper.entityPageToDomainPage(campaignEntities);
    }
    @Override
    public Optional<Campaign> findOneCampaignById(Integer campaignId) {
        return this.campaignJpaRepository.findById(campaignId).map(CampaignPersistenceMapper::entityToDomain);
    }
    @Override
    public Boolean existsOneCampaignById(Integer campaignId) {
        return this.campaignJpaRepository.existsById(campaignId);
    }
    @Override
    @Transactional
    public void updateOneCampaign(Integer campaignId, Campaign campaign) {
        this.campaignJpaRepository.save(CampaignPersistenceMapper.domainToEntity(campaign));
    }
    @Override
    @Transactional
    public void deleteOneCampaign(Integer campaignId) {
        this.campaignJpaRepository.deleteById(campaignId);
    }
    private Pageable buildPageable(CampaignFilters campaignFilters) {
        return PageRequest.of(campaignFilters.getPage().getNumber(), campaignFilters.getPage().getSize(), Sort.by(Sort.Direction.ASC, "id"));
    }
}
