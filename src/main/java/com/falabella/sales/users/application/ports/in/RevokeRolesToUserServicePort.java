package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;

import java.util.List;

public interface RevokeRolesToUserServicePort {
    void execute(Long userId, List<Integer> roleIds) throws UserNotFoundException, RoleNotFoundException;
}
