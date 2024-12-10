package com.falabella.sales.users.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.users.application.ports.in.FindUsersServicePort;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserQueryRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserResponse;

import com.falabella.sales.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('Admin')")
public class FindUsersRestController {
    private final FindUsersServicePort findUsersServicePort;
    public FindUsersRestController(FindUsersServicePort findUsersServicePort) {
        this.findUsersServicePort = findUsersServicePort;
    }
    @GetMapping
    public ResponseEntity<PaginationResultResponse<UserResponse>> findUsers(
        @ModelAttribute @Valid UserQueryRequest userQueryRequest
    ) {
        UserFilters userFilters = UserRestMapper.queryRequestToFilters(userQueryRequest);
        PaginationResult<User> userPage = this.findUsersServicePort.execute(userFilters);
        if (userPage.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(UserRestMapper.domainToPaginationResponse(userPage));
    }
}
