package com.falabella.sales.users.infrastructure.adapters.out.persistence.mappers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;

import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RolePersistenceMapper {
    private RolePersistenceMapper() {}
    public static Role entityToDomain(RoleEntity roleEntity) {
        return Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .description(roleEntity.getDescription())
            .createdAt(roleEntity.getCreatedAt())
            .updatedAt(roleEntity.getUpdatedAt())
            .build();
    }
    public static List<Role> entityListToDomainList(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
            .map(RolePersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<Role> entityPageToDomainPage(Page<RoleEntity> roleEntityPage) {
        return PaginationResult.<Role>builder()
            .totalItems(roleEntityPage.getTotalElements())
            .totalPages(roleEntityPage.getTotalPages())
            .currentPage(roleEntityPage.getNumber())
            .pageSize(roleEntityPage.getSize())
            .hasNextPage(roleEntityPage.hasNext())
            .items(entityListToDomainList(roleEntityPage.getContent()))
            .build();
    }
    public static Set<Role> entitySetToDomainSet(Set<RoleEntity> roleEntities) {
        return Optional.ofNullable(roleEntities)
            .orElse(Collections.emptySet())
            .stream()
            .map(RolePersistenceMapper::entityToDomain)
            .collect(Collectors.toSet());
    }
}
