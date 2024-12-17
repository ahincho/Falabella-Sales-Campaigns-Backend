package com.falabella.sales.campaigns.domain.models;

import com.falabella.sales.commons.domain.models.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFilters {
    private Page page;
}
