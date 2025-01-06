package com.falabella.sales.campaigns.infrastructure.in.rest.controllers;

import com.falabella.sales.campaigns.application.ports.in.FindCampaignsServicePort;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignQueryRequest;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignResponse;
import com.falabella.sales.campaigns.infrastructure.in.rest.mappers.CampaignRestMapper;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/campaigns")
@PreAuthorize("hasAnyRole('Admin', 'Operator', 'Client')")
public class FindCampaignsRestController {
    private final FindCampaignsServicePort findCampaignsServicePort;
    public FindCampaignsRestController(FindCampaignsServicePort findCampaignsServicePort) {
        this.findCampaignsServicePort = findCampaignsServicePort;
    }
    @GetMapping
    public ResponseEntity<PaginationResultResponse<CampaignResponse>> findCampaigns(
        @ModelAttribute @Valid CampaignQueryRequest campaignQueryRequest
    ) {
        CampaignFilters campaignFilters = CampaignRestMapper.queryRequestToFilters(campaignQueryRequest);
        PaginationResult<Campaign> campaignPage = this.findCampaignsServicePort.execute(campaignFilters);
        if (campaignPage.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CampaignRestMapper.domainToPaginationResponse(campaignPage));
    }
}
