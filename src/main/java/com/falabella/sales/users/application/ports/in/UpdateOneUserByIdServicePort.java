package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;

public interface UpdateOneUserByIdServicePort {
    void execute(Long userId, User user) throws UserNotFoundException, UserDuplicatedException;
}
