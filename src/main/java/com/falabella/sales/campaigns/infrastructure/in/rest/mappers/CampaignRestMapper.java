package com.falabella.sales.campaigns.infrastructure.in.rest.mappers;

import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignQueryRequest;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignResponse;
import com.falabella.sales.commons.domain.models.Page;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.utils.IntegerUtils;

import java.util.List;

public class CampaignRestMapper {
    private CampaignRestMapper() {}
    public static CampaignResponse domainToResponse(Campaign campaign) {
        return CampaignResponse.builder()
            .id(campaign.getId())
            .name(campaign.getName())
            .description(campaign.getDescription())
            .startDate(campaign.getStartDate())
            .endDate(campaign.getEndDate())
            .loginMessage(campaign.getLoginMessage())
            .loginImage(campaign.getLoginImage())
            .backgroundColor(campaign.getBackgroundColor())
            .homeMessage(campaign.getHomeMessage())
            .homeMessageImage(campaign.getHomeMessageImage())
            .messageTextColor(campaign.getMessageTextColor())
            .couponUsageMessage(campaign.getCouponUsageMessage())
            .legalConditions(campaign.getLegalConditions())
            .createdAt(campaign.getCreatedAt())
            .updatedAt(campaign.getUpdatedAt())
            .build();
    }
    public static CampaignFilters queryRequestToFilters(CampaignQueryRequest campaignQueryRequest) {
        return CampaignFilters.builder()
            .page(
                Page.builder()
                    .number(IntegerUtils.safeParseInteger(campaignQueryRequest.getPage(), 0))
                    .size(IntegerUtils.safeParseInteger(campaignQueryRequest.getSize(), 10))
                    .build()
            )
            .build();
    }
    public static PaginationResultResponse<CampaignResponse> domainToPaginationResponse(PaginationResult<Campaign> campaignPaginationResult) {
        return PaginationResultResponse.<CampaignResponse>builder()
            .totalItems(campaignPaginationResult.getTotalItems())
            .totalPages(campaignPaginationResult.getTotalPages())
            .currentPage(campaignPaginationResult.getCurrentPage())
            .pageSize(campaignPaginationResult.getPageSize())
            .hasNextPage(campaignPaginationResult.getHasNextPage())
            .items(domainListToResponseList(campaignPaginationResult.getItems()))
            .build();
    }
    private static List<CampaignResponse> domainListToResponseList(List<Campaign> campaigns) {
        return campaigns.stream().map(CampaignRestMapper::domainToResponse).toList();
    }
}
