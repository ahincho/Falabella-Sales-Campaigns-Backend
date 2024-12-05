package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.UpdateOneUserByIdServicePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOneUserByIdService implements UpdateOneUserByIdServicePort {
    private final UserPersistencePort userPersistencePort;
    public UpdateOneUserByIdService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void execute(Long userId, User user) throws UserNotFoundException, UserDuplicatedException {
        Optional<User> existingUser = this.userPersistencePort.findOneUserById(userId);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User with id '" + userId + "' not found");
        }
        this.userPersistencePort.updateOneUserById(userId, user);
    }
}
