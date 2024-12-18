package com.falabella.sales.notifications.infrastructure.adapters.out.persistence.mappers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.infrastructure.adapters.out.persistence.entities.NotificationEntity;

import org.springframework.data.domain.Page;

import java.util.List;

public class NotificationPersistenceMapper {
    private NotificationPersistenceMapper() {}
    public static NotificationEntity domainToEntity(Notification notification) {
        return NotificationEntity.builder()
            .id(notification.getId() != null ? notification.getId() : null)
            .title(notification.getTitle())
            .message(notification.getMessage())
            .status(notification.getStatus())
            .createdAt(notification.getCreatedAt() != null ? notification.getCreatedAt() : null)
            .updatedAt(notification.getUpdatedAt() != null ? notification.getUpdatedAt() : null)
            .build();
    }
    public static Notification entityToDomain(NotificationEntity notificationEntity) {
        return Notification.builder()
            .id(notificationEntity.getId())
            .title(notificationEntity.getTitle())
            .message(notificationEntity.getMessage())
            .status(notificationEntity.getStatus())
            .createdAt(notificationEntity.getCreatedAt())
            .updatedAt(notificationEntity.getUpdatedAt())
            .build();
    }
    public static List<Notification> entityListToDomainList(List<NotificationEntity> notificationEntities) {
        return notificationEntities.stream()
            .map(NotificationPersistenceMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<Notification> entityPageToDomainPage(Page<NotificationEntity> notificationEntityPage) {
        return PaginationResult.<Notification>builder()
            .totalItems(notificationEntityPage.getTotalElements())
            .totalPages(notificationEntityPage.getTotalPages())
            .currentPage(notificationEntityPage.getNumber())
            .pageSize(notificationEntityPage.getSize())
            .hasNextPage(notificationEntityPage.hasNext())
            .items(entityListToDomainList(notificationEntityPage.getContent()))
            .build();
    }
}
