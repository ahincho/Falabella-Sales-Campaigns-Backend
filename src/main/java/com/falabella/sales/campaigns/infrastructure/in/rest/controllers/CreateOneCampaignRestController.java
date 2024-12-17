package com.falabella.sales.campaigns.infrastructure.in.rest.controllers;

import com.falabella.sales.campaigns.application.ports.in.CreateOneCampaignServicePort;
import com.falabella.sales.campaigns.infrastructure.in.rest.dtos.CampaignResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CreateOneCampaignRestController {
    private final CreateOneCampaignServicePort createOneCampaignServicePort;
    public CreateOneCampaignRestController(CreateOneCampaignServicePort createOneCampaignServicePort) {
        this.createOneCampaignServicePort = createOneCampaignServicePort;
    }
    @PostMapping
    public ResponseEntity<CampaignResponse> createOneCampaign() {
        return ResponseEntity.noContent().build();
    }
}
