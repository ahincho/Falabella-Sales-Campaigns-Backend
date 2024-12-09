package com.falabella.sales.users.infrastructure.adapters.out.persistence.mappers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.UserEntity;

import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserPersistenceMapper {
    private UserPersistenceMapper() {}
    public static UserEntity domainToEntity(User user) {
        return UserEntity.builder()
            .id(user.getId() != null ? user.getId() : null)
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .username(user.getUsername())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt() != null ? user.getCreatedAt() : null)
            .build();
    }
    public static User entityToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .firstname(userEntity.getFirstname())
            .lastname(userEntity.getLastname())
            .username(userEntity.getUsername())
            .email(userEntity.getEmail())
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .roles(Optional.ofNullable(userEntity.getRoles())
                .map(RolePersistenceMapper::entitySetToDomainSet)
                .orElse(Collections.emptySet()))
            .build();
    }
    public static User entityWithNoRolesToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .firstname(userEntity.getFirstname())
            .lastname(userEntity.getLastname())
            .username(userEntity.getUsername())
            .email(userEntity.getEmail())
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
    }
    public static List<User> entityListToDomainList(List<UserEntity> userEntities) {
        return userEntities.stream()
            .map(UserPersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<User> entityPageToDomainPage(Page<UserEntity> userEntityPage) {
        return PaginationResult.<User>builder()
            .totalItems(userEntityPage.getTotalElements())
            .totalPages(userEntityPage.getTotalPages())
            .currentPage(userEntityPage.getNumber())
            .pageSize(userEntityPage.getSize())
            .hasNextPage(userEntityPage.hasNext())
            .items(entityListToDomainList(userEntityPage.getContent()))
            .build();
    }
}
