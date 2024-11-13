package com.falabella.sales.users.application.ports.in;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;

public interface FindUsersServicePort {
    PaginationResult<User> execute(UserFilters userFilters);
}
