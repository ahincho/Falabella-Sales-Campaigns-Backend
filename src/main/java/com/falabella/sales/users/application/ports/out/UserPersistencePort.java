package com.falabella.sales.users.application.ports.out;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    User assignRolesToUser(Long userId, List<Integer> roleIds) throws UserNotFoundException;
    PaginationResult<User> findUsers(UserFilters userFilters);
    Optional<User> findOneUserById(Long userId);
    Optional<User> findOneUserByUsername(String username);
    Optional<User> findOneUserByEmail(String email);
    Boolean existsOneUserById(Long userId);
    Boolean existsOneUserByUsername(String username);
    Boolean existsOneUserByEmail(String email);
    void updateOneUserById(Long userId, User user);
    void deleteOneUserById(Long userId);
    void revokeRolesToUser(Long userId, List<Integer> roleIds) throws UserNotFoundException;
}
