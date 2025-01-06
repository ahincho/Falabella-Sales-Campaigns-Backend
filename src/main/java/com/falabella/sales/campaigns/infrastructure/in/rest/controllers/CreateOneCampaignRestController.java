package com.falabella.sales.campaigns.infrastructure.in.rest.controllers;

import com.falabella.sales.campaigns.application.ports.in.CreateOneCampaignServicePort;
import com.falabella.sales.campaigns.domain.exceptions.CampaignDuplicatedException;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignCreateRequest;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignResponse;
import com.falabella.sales.campaigns.infrastructure.in.rest.mappers.CampaignRestMapper;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CreateOneCampaignRestController {
    private final CreateOneCampaignServicePort createOneCampaignServicePort;
    public CreateOneCampaignRestController(CreateOneCampaignServicePort createOneCampaignServicePort) {
        this.createOneCampaignServicePort = createOneCampaignServicePort;
    }
    @PostMapping
    public ResponseEntity<CampaignResponse> createOneCampaign(
        @ModelAttribute @Valid CampaignCreateRequest campaignCreateRequest
    ) throws IOException, CampaignDuplicatedException, FileDuplicatedException {
        File loginImage = CampaignRestMapper.multipartFileToDomainFile(campaignCreateRequest.getLoginImage());
        File homeImage = CampaignRestMapper.multipartFileToDomainFile(campaignCreateRequest.getHomeImage());
        File homeMessageImage = CampaignRestMapper.multipartFileToDomainFile(campaignCreateRequest.getHomeMessageImage());
        Campaign campaign = CampaignRestMapper.createRequestToDomain(campaignCreateRequest);
        Campaign savedCampaign = this.createOneCampaignServicePort.execute(campaign, loginImage, homeImage, homeMessageImage);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{campaignId}").buildAndExpand(savedCampaign.getId()).toUri();
        return ResponseEntity.created(uri).body(CampaignRestMapper.domainToResponse(savedCampaign));
    }
}
