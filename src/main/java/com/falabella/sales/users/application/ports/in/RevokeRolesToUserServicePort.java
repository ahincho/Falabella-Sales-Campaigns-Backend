package com.falabella.sales.users.application.ports.in;

import java.util.List;

public interface RevokeRolesToUserServicePort {
    void execute(Long userId, List<Integer> roleIds);
}
