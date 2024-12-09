package com.falabella.sales.users.infrastructure.adapters.out.persistence.specifications;

import com.falabella.sales.users.infrastructure.adapters.out.persistence.entities.UserEntity;

import org.springframework.data.jpa.domain.Specification;

public class UserQuerySpecifications {
    private UserQuerySpecifications() {}
    public static Specification<UserEntity> firstnameContains(String firstname) {
        return contains("firstname", firstname);
    }
    public static Specification<UserEntity> lastnameContains(String lastname) {
        return contains("lastname", lastname);
    }
    public static Specification<UserEntity> usernameContains(String username) {
        return contains("username", username);
    }
    private static Specification<UserEntity> contains(String fieldName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty() || value.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }
}
