package com.falabella.sales.campaigns.application.ports.in;

import com.falabella.sales.campaigns.domain.exceptions.CampaignDuplicatedException;
import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;

public interface CreateOneCampaignServicePort {
    Campaign execute(Campaign campaign, File loginImage, File homeMessageImage) throws CampaignDuplicatedException, FileDuplicatedException;
}
