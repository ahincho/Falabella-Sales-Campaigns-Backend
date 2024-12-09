package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;

import java.util.List;

public interface AssignRolesToUserServicePort {
    User execute(Long userId, List<Integer> roleIds) throws UserNotFoundException, RoleNotFoundException;
}
