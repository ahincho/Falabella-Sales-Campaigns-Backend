package com.falabella.sales.users.application.services;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.in.FindRolesServicePort;
import com.falabella.sales.users.application.ports.out.RolePersistencePort;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;

import org.springframework.stereotype.Service;

@Service
public class FindRolesService implements FindRolesServicePort {
    private final RolePersistencePort rolePersistencePort;
    public FindRolesService(RolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }
    @Override
    public PaginationResult<Role> execute(RoleFilters roleFilters) {
        return this.rolePersistencePort.findRoles(roleFilters);
    }
}
