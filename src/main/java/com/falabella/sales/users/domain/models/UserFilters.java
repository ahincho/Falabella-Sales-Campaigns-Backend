package com.falabella.sales.users.domain.models;

import com.falabella.sales.commons.domain.models.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilters {
    private Page page;
    private String firstname;
    private String lastname;
    private String username;
}
