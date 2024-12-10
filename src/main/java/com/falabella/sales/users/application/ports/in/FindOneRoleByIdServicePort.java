package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.models.Role;

public interface FindOneRoleByIdServicePort {
    Role execute(Integer roleId) throws RoleNotFoundException;
}
