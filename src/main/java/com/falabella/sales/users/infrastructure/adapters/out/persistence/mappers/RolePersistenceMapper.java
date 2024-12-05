package com.falabella.sales.users.infrastructure.adapters.out.persistence.mappers;

import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;

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
}
