package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.users.application.ports.in.FindOneRoleByIdServicePort;

import com.falabella.sales.users.domain.exceptions.RoleNotFoundException;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.RoleResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.RoleRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@PreAuthorize("hasRole('Admin')")
public class FindOneRoleByIdRestController {
    private final FindOneRoleByIdServicePort findOneRoleByIdServicePort;
    public FindOneRoleByIdRestController(FindOneRoleByIdServicePort findOneRoleByIdServicePort) {
        this.findOneRoleByIdServicePort = findOneRoleByIdServicePort;
    }
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> findOneRoleById(@PathVariable Integer roleId) throws RoleNotFoundException {
        Role role = this.findOneRoleByIdServicePort.execute(roleId);
        return ResponseEntity.ok(RoleRestMapper.domainToResponse(role));
    }
}
