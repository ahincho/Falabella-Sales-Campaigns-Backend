package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.AssignRolesToUserServicePort;
import com.falabella.sales.users.application.ports.out.RolePersistencePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignRolesToUserService implements AssignRolesToUserServicePort {
    private final RolePersistencePort rolePersistencePort;
    private final UserPersistencePort userPersistencePort;
    public AssignRolesToUserService(
        RolePersistencePort rolePersistencePort,
        UserPersistencePort userPersistencePort
    ) {
        this.rolePersistencePort = rolePersistencePort;
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(Long userId, List<Integer> roleIds) throws UserNotFoundException, RoleNotFoundException {
        if (!this.userPersistencePort.existsOneUserById(userId)) {
            throw new UserNotFoundException("User with id '" + userId + "' not found");
        }
        List<Role> roles = this.rolePersistencePort.findRolesInIds(roleIds);
        Set<Integer> foundRolesIds = roles.stream()
            .map(Role::getId)
            .collect(Collectors.toSet());
        List<Integer> missingRoleIds = roleIds.stream()
            .filter(roleId -> !foundRolesIds.contains(roleId))
            .toList();
        if (!missingRoleIds.isEmpty()) {
            String missingIds = missingRoleIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            throw new RoleNotFoundException("Role(s) with id(s) '" + missingIds + "' not found");
        }
        return this.userPersistencePort.assignRolesToUser(userId, roleIds);
    }
}