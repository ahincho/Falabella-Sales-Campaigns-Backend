package com.falabella.sales.users.application.ports.out;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;

import java.util.Optional;

public interface RolePersistencePort {
    PaginationResult<Role> findRoles(RoleFilters roleFilters);
    Optional<Role> findRoleById(Integer roleId);
    Optional<Role> findRoleByName(String roleName);
}
