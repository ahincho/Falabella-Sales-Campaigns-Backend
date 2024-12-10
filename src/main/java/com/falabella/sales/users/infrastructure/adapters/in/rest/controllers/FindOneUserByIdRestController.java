package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.FindOneUserByIdServicePort;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('Admin')")
public class FindOneUserByIdRestController {
    private final FindOneUserByIdServicePort findOneUserByIdServicePort;
    public FindOneUserByIdRestController(FindOneUserByIdServicePort findOneUserByIdServicePort) {
        this.findOneUserByIdServicePort = findOneUserByIdServicePort;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findOneUserById(@PathVariable Long userId) throws UserNotFoundException {
        User user = this.findOneUserByIdServicePort.execute(userId);
        return ResponseEntity.ok(UserRestMapper.domainToResponse(user));
    }
}
