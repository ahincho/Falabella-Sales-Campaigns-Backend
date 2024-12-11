package com.falabella.sales.commons.infrastructure.configurations.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.falabella.sales.commons.application.services.Auth0JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final Auth0JwtService auth0JwtService;
    public JwtAuthorizationFilter(Auth0JwtService auth0JwtService) {
        this.auth0JwtService = auth0JwtService;
    }
    @Override
    protected void doFilterInternal(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String jwtTokenHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (jwtTokenHeader != null) {
            String jwtToken = jwtTokenHeader.substring(7);
            DecodedJWT decodedJWT = this.auth0JwtService.verifyToken(jwtToken);
            String username = auth0JwtService.getUsernameFromToken(decodedJWT);
            String authorities = auth0JwtService.getClaimFromToken(decodedJWT, "authorities").asString();
            Collection<? extends GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
