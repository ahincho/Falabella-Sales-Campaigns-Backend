package com.falabella.sales.files.domain.models;

import com.falabella.sales.commons.domain.models.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileFilters {
    private Page page;
}
