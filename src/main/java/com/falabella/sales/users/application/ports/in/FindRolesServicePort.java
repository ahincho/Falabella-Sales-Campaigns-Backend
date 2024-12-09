package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;

public interface FindRolesServicePort {
    PaginationResult<Role> execute(RoleFilters roleFilters);
}
