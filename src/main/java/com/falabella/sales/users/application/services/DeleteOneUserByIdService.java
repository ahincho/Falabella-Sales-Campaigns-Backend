package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.DeleteOneUserByIdServicePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class DeleteOneUserByIdService implements DeleteOneUserByIdServicePort {
    private final UserPersistencePort userPersistencePort;
    public DeleteOneUserByIdService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void execute(Long userId) throws UserNotFoundException {
        if (!this.userPersistencePort.existsOneUserById(userId)) {
            throw new UserNotFoundException("User with id '" + userId + "' not found");
        }
        this.userPersistencePort.deleteOneUserById(userId);
    }
}
