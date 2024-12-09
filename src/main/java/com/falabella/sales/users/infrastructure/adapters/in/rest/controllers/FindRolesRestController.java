package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.users.application.ports.in.FindRolesServicePort;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.RoleQueryRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.RoleResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.RoleRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class FindRolesRestController {
    private final FindRolesServicePort findRolesServicePort;
    public FindRolesRestController(FindRolesServicePort findRolesServicePort) {
        this.findRolesServicePort = findRolesServicePort;
    }
    @GetMapping
    public ResponseEntity<PaginationResultResponse<RoleResponse>> findRoles(
        @ModelAttribute @Valid RoleQueryRequest roleQueryRequest
    ) {
        RoleFilters roleFilters = RoleRestMapper.queryRequestToFilters(roleQueryRequest);
        PaginationResult<Role> rolePage = this.findRolesServicePort.execute(roleFilters);
        if (rolePage.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(RoleRestMapper.domainToPaginationResponse(rolePage));
    }
}
