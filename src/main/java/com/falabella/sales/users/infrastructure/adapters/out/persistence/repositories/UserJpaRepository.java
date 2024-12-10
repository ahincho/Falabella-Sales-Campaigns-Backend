package com.falabella.sales.users.infrastructure.adapters.out.persistence.repositories;

import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @EntityGraph(attributePaths = {"roles"})
    Page<UserEntity> findAll(Specification<UserEntity> specification, Pageable pageable);
    @EntityGraph(attributePaths = {"roles"})
    Optional<UserEntity> findById(Long userId);
    @EntityGraph(attributePaths = {"roles"})
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
    Optional<UserEntity> findByEmailIgnoreCase(String email);
    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByEmailIgnoreCase(String email);
}
