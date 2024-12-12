package com.falabella.sales.commons.application.services;

import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.AuthRequest;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.AuthResponse;
import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.models.User;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserPersistencePort userPersistencePort;
    private final Auth0JwtService auth0JwtService;
    private final PasswordEncoder passwordEncoder;
    public UserDetailsServiceImpl(
        UserPersistencePort userPersistencePort,
        Auth0JwtService auth0JwtService,
        PasswordEncoder passwordEncoder
    ) {
        this.userPersistencePort = userPersistencePort;
        this.auth0JwtService = auth0JwtService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = this.userPersistencePort.findOneUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("Bad credentials");
        }
        User user = optionalUser.get();
        Collection<? extends GrantedAuthority> grantedAuthorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorities);
    }
    public AuthResponse login(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = this.auth0JwtService.generateToken(authentication);
        return AuthResponse.builder()
            .username(username)
            .message("Successfully logged in")
            .token(accessToken)
            .status(true)
            .build();
    }
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User with username '" + username + "' not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
