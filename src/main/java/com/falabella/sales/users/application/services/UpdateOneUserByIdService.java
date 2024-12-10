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
        Optional<User> optionalExistingUser = this.userPersistencePort.findOneUserById(userId);
        if (optionalExistingUser.isEmpty()) {
            throw new UserNotFoundException("User with id '" + userId + "' not found");
        }
        if (user.getUsername() != null) {
            Optional<User> existingUserByUsername = this.userPersistencePort.findOneUserByUsername(user.getUsername());
            if (existingUserByUsername.isPresent() && !existingUserByUsername.get().getId().equals(userId)) {
                throw new UserDuplicatedException("User with username '" + user.getUsername() + "' already exists");
            }
        }
        if (user.getEmail() != null) {
            Optional<User> existingUserByEmail = this.userPersistencePort.findOneUserByEmail(user.getEmail());
            if (existingUserByEmail.isPresent() && !existingUserByEmail.get().getId().equals(userId)) {
                throw new UserDuplicatedException("User with email '" + user.getEmail() + "' already exists");
            }
        }
        User existingUser = optionalExistingUser.get();
        if (user.getFirstname() != null) {
            existingUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            existingUser.setLastname(user.getLastname());
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername().toLowerCase());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail().toLowerCase());
        }
        user.setCreatedAt(existingUser.getCreatedAt());
        user.setPassword(existingUser.getPassword());
        this.userPersistencePort.updateOneUserById(userId, user);
    }
}
