package com.falabella.sales.campaigns.infrastructure.out.persistence.mappers;

import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.infrastructure.out.persistence.entities.CampaignEntity;
import com.falabella.sales.commons.domain.models.PaginationResult;

import org.springframework.data.domain.Page;

import java.util.List;

public class CampaignPersistenceMapper {
    private CampaignPersistenceMapper() {}
    public static CampaignEntity domainToEntity(Campaign campaign) {
        return CampaignEntity.builder()
            .id(campaign.getId() != null ? campaign.getId() : null)
            .name(campaign.getName())
            .description(campaign.getDescription())
            .startDate(campaign.getStartDate())
            .endDate(campaign.getEndDate())
            .loginMessage(campaign.getLoginMessage())
            .loginImage(campaign.getLoginImage())
            .backgroundColor(campaign.getBackgroundColor())
            .homeImage(campaign.getHomeImage())
            .homeMessage(campaign.getHomeMessage())
            .homeMessageImage(campaign.getHomeMessageImage())
            .messageTextColor(campaign.getMessageTextColor())
            .couponUsageMessage(campaign.getCouponUsageMessage())
            .legalConditions(campaign.getLegalConditions())
            .createdAt(campaign.getCreatedAt() != null ? campaign.getCreatedAt() : null)
            .build();
    }
    public static Campaign entityToDomain(CampaignEntity campaignEntity) {
        return Campaign.builder()
            .id(campaignEntity.getId())
            .name(campaignEntity.getName())
            .description(campaignEntity.getDescription())
            .startDate(campaignEntity.getStartDate())
            .endDate(campaignEntity.getEndDate())
            .loginMessage(campaignEntity.getLoginMessage())
            .loginImage(campaignEntity.getLoginImage())
            .backgroundColor(campaignEntity.getBackgroundColor())
            .homeImage(campaignEntity.getHomeImage())
            .homeMessage(campaignEntity.getHomeMessage())
            .homeMessageImage(campaignEntity.getHomeMessageImage())
            .messageTextColor(campaignEntity.getMessageTextColor())
            .couponUsageMessage(campaignEntity.getCouponUsageMessage())
            .legalConditions(campaignEntity.getLegalConditions())
            .createdAt(campaignEntity.getCreatedAt())
            .updatedAt(campaignEntity.getUpdatedAt())
            .build();
    }
    public static List<Campaign> entityListToDomainList(List<CampaignEntity> campaignEntities) {
        return campaignEntities.stream()
            .map(CampaignPersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<Campaign> entityPageToDomainPage(Page<CampaignEntity> campaignEntityPage) {
        return PaginationResult.<Campaign>builder()
            .totalItems(campaignEntityPage.getTotalElements())
            .totalPages(campaignEntityPage.getTotalPages())
            .currentPage(campaignEntityPage.getNumber())
            .pageSize(campaignEntityPage.getSize())
            .hasNextPage(campaignEntityPage.hasNext())
            .items(entityListToDomainList(campaignEntityPage.getContent()))
            .build();
    }
}
