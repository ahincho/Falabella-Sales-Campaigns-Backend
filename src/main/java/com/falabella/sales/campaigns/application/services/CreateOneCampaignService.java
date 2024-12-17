package com.falabella.sales.campaigns.application.services;

import com.falabella.sales.campaigns.application.ports.in.CreateOneCampaignServicePort;
import com.falabella.sales.campaigns.application.ports.out.CampaignPersistencePort;
import com.falabella.sales.campaigns.domain.exceptions.CampaignDuplicatedException;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;

import org.springframework.stereotype.Service;

@Service
public class CreateOneCampaignService implements CreateOneCampaignServicePort {
    private final static String DIRECTORY = "campaigns";
    private final StoragePort storagePort;
    private final CampaignPersistencePort campaignPersistencePort;
    public CreateOneCampaignService(StoragePort storagePort,CampaignPersistencePort campaignPersistencePort) {
        this.storagePort = storagePort;
        this.campaignPersistencePort = campaignPersistencePort;
    }
    @Override
    public Campaign execute(Campaign campaign, File loginImage, File homeMessageImage) throws CampaignDuplicatedException, FileDuplicatedException {
        if (this.storagePort.existsOneFileByName(DIRECTORY, loginImage.getName())) {
            throw new FileDuplicatedException("File with name '" + loginImage.getName() + "' already exists");
        }
        if (this.storagePort.existsOneFileByName(DIRECTORY, homeMessageImage.getName())) {
            throw new FileDuplicatedException("File with name '" + homeMessageImage.getName() + "' already exists");
        }
        File loginImageFile = this.storagePort.createOneFile(DIRECTORY, loginImage);
        File homeMessageImageFile = this.storagePort.createOneFile(DIRECTORY, homeMessageImage);
        campaign.setLoginImage(loginImageFile.getUrl());
        campaign.setHomeMessageImage(homeMessageImageFile.getUrl());
        return this.campaignPersistencePort.createOneCampaign(campaign);
    }
}
