package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;

public interface FindOneUserByIdServicePort {
    User execute(Long userId) throws UserNotFoundException;
}
