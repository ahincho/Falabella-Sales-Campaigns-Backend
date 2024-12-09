package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.FindOneUserByIdServicePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneUserByIdService implements FindOneUserByIdServicePort {
    private final UserPersistencePort userPersistencePort;
    public FindOneUserByIdService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(Long userId) throws UserNotFoundException {
        Optional<User> user = this.userPersistencePort.findOneUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id '" + userId + "' not found");
        }
        return user.get();
    }
}
