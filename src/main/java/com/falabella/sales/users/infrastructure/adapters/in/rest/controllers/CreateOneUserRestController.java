package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.CreateOneUserServicePort;
import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.exceptions.UserDuplicatedException;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserCreateRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class CreateOneUserRestController {
    private final CreateOneUserServicePort createOneUserServicePort;
    public CreateOneUserRestController(CreateOneUserServicePort createOneUserServicePort) {
        this.createOneUserServicePort = createOneUserServicePort;
    }
    @PostMapping
    public ResponseEntity<UserResponse> createOneUser(
        @RequestBody @Valid UserCreateRequest userCreateRequest
    ) throws UserDuplicatedException, RoleNotFoundException {
        User user = UserRestMapper.createRequestToDomain(userCreateRequest);
        User savedUser = this.createOneUserServicePort.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRestMapper.domainToResponse(savedUser));
    }
}
