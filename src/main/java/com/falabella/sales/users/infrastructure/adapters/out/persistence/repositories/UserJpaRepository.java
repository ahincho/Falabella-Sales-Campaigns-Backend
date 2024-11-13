package com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories;

import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByEmailIgnoreCase(String email);
}
