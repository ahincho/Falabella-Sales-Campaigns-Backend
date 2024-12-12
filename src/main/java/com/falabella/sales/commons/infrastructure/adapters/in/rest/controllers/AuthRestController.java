package com.falabella.sales.commons.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.commons.application.services.UserDetailsServiceImpl;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.AuthRequest;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.AuthResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    public AuthRestController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
    @PostMapping
    public ResponseEntity<AuthResponse> getJwtToken(
        @RequestBody @Valid AuthRequest authRequest
    ) {
        return ResponseEntity.ok(this.userDetailsServiceImpl.login(authRequest));
    }
}
