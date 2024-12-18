package com.falabella.sales.notifications.infrastructure.adapters.out.persistence.specifications;

import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.entities.NotificationEntity;

import org.springframework.data.jpa.domain.Specification;

public class NotificationQuerySpecifications {
    private NotificationQuerySpecifications() {}
    public static Specification<NotificationEntity> titleContains(String title) {
        return contains("title", title);
    }
    public static Specification<NotificationEntity> messageContains(String message) {
        return contains("message", message);
    }
    public static Specification<NotificationEntity> senderContains(String sender) {
        return contains("sender", sender);
    }
    private static Specification<NotificationEntity> contains(String fieldName, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty() || value.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }
}
