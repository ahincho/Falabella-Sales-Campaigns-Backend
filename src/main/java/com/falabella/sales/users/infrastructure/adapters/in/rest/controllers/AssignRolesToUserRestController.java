package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.AssignRolesToUserServicePort;
import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserAddRolesRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class AssignRolesToUserRestController {
    private final AssignRolesToUserServicePort assignRolesToUserServicePort;
    public AssignRolesToUserRestController(AssignRolesToUserServicePort assignRolesToUserServicePort) {
        this.assignRolesToUserServicePort = assignRolesToUserServicePort;
    }
    @PostMapping("/{userId}/roles")
    public ResponseEntity<UserResponse> assignRolesToUser(
        @PathVariable Long userId,
        @RequestBody @Valid UserAddRolesRequest userAddRolesRequest
    ) throws UserNotFoundException, RoleNotFoundException {
        User savedUser = this.assignRolesToUserServicePort.execute(userId, userAddRolesRequest.getRoleIds());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRestMapper.domainToResponse(savedUser));
    }
}
