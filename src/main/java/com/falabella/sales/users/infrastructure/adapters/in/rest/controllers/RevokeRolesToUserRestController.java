package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.RevokeRolesToUserServicePort;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserRevokeRolesRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class RevokeRolesToUserRestController {
    private final RevokeRolesToUserServicePort revokeRolesToUserServicePort;
    public RevokeRolesToUserRestController(RevokeRolesToUserServicePort revokeRolesToUserServicePort) {
        this.revokeRolesToUserServicePort = revokeRolesToUserServicePort;
    }
    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Void> revokeRolesToUser(
        @PathVariable Long userId,
        @RequestBody UserRevokeRolesRequest userRevokeRolesRequest
    ) throws UserNotFoundException, RoleNotFoundException {
        this.revokeRolesToUserServicePort.execute(userId, userRevokeRolesRequest.getRoleIds());
        return ResponseEntity.noContent().build();
    }
}
