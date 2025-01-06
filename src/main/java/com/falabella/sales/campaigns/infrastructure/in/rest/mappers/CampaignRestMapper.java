package com.falabella.sales.campaigns.infrastructure.in.rest.mappers;

import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignCreateRequest;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignQueryRequest;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignResponse;
import com.falabella.sales.commons.domain.models.Page;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.utils.IntegerUtils;
import com.falabella.sales.files.domain.models.ContentType;
import com.falabella.sales.files.domain.models.File;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
            .homeImage(campaign.getHomeImage())
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
    public static File multipartFileToDomainFile(MultipartFile multipartFile) throws IOException {
        String mimeType = Objects.requireNonNull(multipartFile.getContentType()).toLowerCase();
        ContentType contentType = ContentType.fromString(mimeType);
        return File.builder()
            .id(multipartFile.getOriginalFilename())
            .name(multipartFile.getOriginalFilename())
            .size(multipartFile.getSize())
            .content(multipartFile.getInputStream())
            .type(contentType)
            .build();
    }
    public static Campaign createRequestToDomain(CampaignCreateRequest campaignCreateRequest) {
        return Campaign.builder()
            .id(null)
            .name(campaignCreateRequest.getName())
            .description(campaignCreateRequest.getDescription())
            .startDate(campaignCreateRequest.getStartDate())
            .endDate(campaignCreateRequest.getEndDate())
            .loginMessage(campaignCreateRequest.getLoginMessage())
            .backgroundColor(campaignCreateRequest.getBackgroundColor())
            .homeMessage(campaignCreateRequest.getHomeMessage())
            .messageTextColor(campaignCreateRequest.getMessageTextColor())
            .couponUsageMessage(campaignCreateRequest.getCouponUsageMessage())
            .legalConditions(campaignCreateRequest.getLegalConditions())
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
