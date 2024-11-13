package com.falabella.sales.users.application.ports.out;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;

import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    PaginationResult<User> findUsers(UserFilters userFilters);
    Optional<User> findOneUserById(Long userId);
    Boolean existsOneUserById(Long userId);
    Boolean existsOneUserByUsername(String username);
    Boolean existsOneUserByEmail(String email);
    void updateOneUserById(Long userId, User user);
    void deleteOneUserById(Long userId);
}
