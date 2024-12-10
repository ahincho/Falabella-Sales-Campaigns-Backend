package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.DeleteOneUserByIdServicePort;
import com.falabella.sales.users.domain.exceptions.UserNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('Admin')")
public class DeleteOneUserByIdRestController {
    private final DeleteOneUserByIdServicePort deleteOneUserByIdServicePort;
    public DeleteOneUserByIdRestController(DeleteOneUserByIdServicePort deleteOneUserByIdServicePort) {
        this.deleteOneUserByIdServicePort = deleteOneUserByIdServicePort;
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteOneUserById(@PathVariable Long userId) throws UserNotFoundException {
        this.deleteOneUserByIdServicePort.execute(userId);
        return ResponseEntity.noContent().build();
    }
}
