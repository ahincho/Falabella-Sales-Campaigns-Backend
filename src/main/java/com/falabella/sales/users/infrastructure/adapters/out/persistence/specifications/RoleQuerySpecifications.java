package com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications;

import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.RoleEntity;

import org.springframework.data.jpa.domain.Specification;

public class RoleQuerySpecifications {
    public static Specification<RoleEntity> nameContains(String name) {
        return contains("name", name);
    }
    public static Specification<RoleEntity> descriptionContains(String description) {
        return contains("description", description);
    }
    private static Specification<RoleEntity> contains(String fieldName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty() || value.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }
}
