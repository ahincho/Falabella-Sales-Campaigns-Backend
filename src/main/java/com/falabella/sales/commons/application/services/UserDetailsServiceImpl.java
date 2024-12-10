package com.falabella.sales.commons.application.services;

import com.falabella.sales.users.application.ports.out.UserPersistencePort;
import com.falabella.sales.users.domain.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserPersistencePort userPersistencePort;
    public UserDetailsServiceImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with username '" + username + "' not found");
        }
        User user = optionalUser.get();
        Collection<? extends GrantedAuthority> grantedAuthorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
