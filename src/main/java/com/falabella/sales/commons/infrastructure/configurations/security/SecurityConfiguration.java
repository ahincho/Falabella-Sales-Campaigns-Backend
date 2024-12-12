package com.falabella.sales.commons.infrastructure.configurations.security;

import com.falabella.sales.commons.application.services.Auth0JwtService;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.advices.CustomAccessDeniedHandler;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.advices.CustomAuthenticationEntryPoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final Auth0JwtService auth0JwtService;
    public SecurityConfiguration(
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
        CustomAccessDeniedHandler customAccessDeniedHandler,
        Auth0JwtService auth0JwtService
    ) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.auth0JwtService = auth0JwtService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .httpBasic(Customizer.withDefaults())
            .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll();
                authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll();
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
            })
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(this.customAuthenticationEntryPoint);
                httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(this.customAccessDeniedHandler);
            })
            .addFilterBefore(new JwtValidationFilter(auth0JwtService), BasicAuthenticationFilter.class)
            .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200", "https://www.google.com"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }
}
