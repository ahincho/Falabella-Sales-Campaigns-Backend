package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.UpdateOneUserByIdServicePort;
import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserUpdateRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('Admin')")
public class UpdateOneUserByIdRestController {
    private final UpdateOneUserByIdServicePort updateOneUserByIdServicePort;
    public UpdateOneUserByIdRestController(UpdateOneUserByIdServicePort updateOneUserByIdServicePort) {
        this.updateOneUserByIdServicePort = updateOneUserByIdServicePort;
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateOneUserById(
        @PathVariable Long userId,
        @RequestBody @Valid UserUpdateRequest userUpdateRequest
    ) throws UserNotFoundException, UserDuplicatedException {
        User user = UserRestMapper.updateRequestToDomain(userId, userUpdateRequest);
        this.updateOneUserByIdServicePort.execute(userId, user);
        return ResponseEntity.noContent().build();
    }
}
