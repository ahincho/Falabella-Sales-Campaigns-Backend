package com.falabella.sales.campaigns.application.ports.in;

import com.falabella.sales.campaigns.domain.models.Campaign;
import com.falabella.sales.campaigns.domain.models.CampaignFilters;
import com.falabella.sales.commons.domain.models.PaginationResult;

public interface FindCampaignsServicePort {
    PaginationResult<Campaign> execute(CampaignFilters campaignFilters);
}
