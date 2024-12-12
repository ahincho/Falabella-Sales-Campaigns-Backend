package com.falabella.sales.commons.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Auth0JwtService {
    @Value("${jwt.secret}")
    private String privateKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expires}")
    private Long expires;
    public String generateToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        String username = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        return JWT.create()
            .withIssuer(this.issuer)
            .withSubject(username)
            .withClaim("authorities", authorities)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + this.expires))
            .withJWTId(UUID.randomUUID().toString())
            .withNotBefore(new Date(System.currentTimeMillis()))
            .sign(algorithm);
    }
    public DecodedJWT verifyToken(String jwtToken) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(this.issuer)
            .build();
        return verifier.verify(jwtToken);
    }
    public String getUsernameFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }
    public Claim getClaimFromToken(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
    public Map<String, Claim> getClaimsFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }
}
