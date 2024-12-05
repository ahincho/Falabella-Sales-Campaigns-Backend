package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.users.domain.exceptions.UserNotFoundException;

public interface DeleteOneUserByIdServicePort {
    void execute(Long userId) throws UserNotFoundException;
}
