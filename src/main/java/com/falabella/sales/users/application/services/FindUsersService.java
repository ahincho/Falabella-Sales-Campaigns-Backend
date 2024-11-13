package com.falabella.sales.users.application.services;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.application.ports.in.FindUsersServicePort;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;

import org.springframework.stereotype.Service;

@Service
public class FindUsersService implements FindUsersServicePort {
    private final UserPersistencePort userPersistencePort;
    public FindUsersService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public PaginationResult<User> execute(UserFilters userFilters) {
        return this.userPersistencePort.findUsers(userFilters);
    }
}
