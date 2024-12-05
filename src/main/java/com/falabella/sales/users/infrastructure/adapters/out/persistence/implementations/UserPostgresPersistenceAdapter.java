package com.falabella.sales.users.infrastructure.adapters.out.persistence.implementations;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories.UserJpaRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPostgresPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    public UserPostgresPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    @Override
    public User createOneUser(User user) {
        return null;
    }
    @Override
    public PaginationResult<User> findUsers(UserFilters userFilters) {
        return null;
    }
    @Override
    public Optional<User> findOneUserById(Long userId) {
        return Optional.empty();
    }
    @Override
    public Boolean existsOneUserById(Long userId) {
        return null;
    }
    @Override
    public Boolean existsOneUserByUsername(String username) {
        return null;
    }
    @Override
    public Boolean existsOneUserByEmail(String email) {
        return null;
    }
    @Override
    public void updateOneUserById(Long userId, User user) {

    }
    @Override
    public void deleteOneUserById(Long userId) {

    }
}
