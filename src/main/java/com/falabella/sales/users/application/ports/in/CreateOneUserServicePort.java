package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.models.User;

public interface CreateOneUserServicePort {
    User execute(User user) throws UserDuplicatedException, RoleNotFoundException;
}
