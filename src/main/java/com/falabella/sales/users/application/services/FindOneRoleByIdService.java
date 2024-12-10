package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.FindOneRoleByIdServicePort;
import com.falabella.sales.users.application.ports.out.RolePersistencePort;
import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.models.Role;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneRoleByIdService implements FindOneRoleByIdServicePort {
    private final RolePersistencePort rolePersistencePort;
    public FindOneRoleByIdService(RolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }
    @Override
    public Role execute(Integer roleId) throws RoleNotFoundException {
        Optional<Role> role = this.rolePersistencePort.findRoleById(roleId);
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role with id '" + roleId + "' not found");
        }
        return role.get();
    }
}
