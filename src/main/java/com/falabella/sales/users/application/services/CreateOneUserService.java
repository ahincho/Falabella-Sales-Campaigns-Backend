package com.falabella.sales.users.application.services;

import com.falabella.sales.users.application.ports.in.CreateOneUserServicePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.models.User;

import org.springframework.stereotype.Service;

@Service
public class CreateOneUserService implements CreateOneUserServicePort {
    private final UserPersistencePort userPersistencePort;
    public CreateOneUserService (UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(User user) throws UserDuplicatedException, RoleNotFoundException {
        if (this.userPersistencePort.existsOneUserByUsername(user.getUsername())) {
            throw new UserDuplicatedException("User with username '" + user.getUsername() + "' already exists");
        }
        if (this.userPersistencePort.existsOneUserByEmail(user.getEmail())) {
            throw new UserDuplicatedException("User with email '" + user.getEmail() + "' already exists");
        }
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        return this.userPersistencePort.createOneUser(user);
    }
}
