package com.falabella.sales.notifications.application.ports.out;

import com.falabella.sales.commons.domain.models.PaginationResult;

import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;

import java.util.List;
import java.util.Optional;

public interface NotificationPersistencePort {
    Notification createOneNotification(Notification notification);
   
    PaginationResult<Notification> findNotifications(NotificationFilters notificationFilters);
    Optional<Notification> findOneNotificationById(Long notificationId);
    Boolean existsOneNotificationById(Long notificationId);
    void updateOneNotificationById(Long notificationId, Notification notification);
    void deleteOneNotificationById(Long notificationId);
    List<Notification> findNotificationsByUserId(Long userId);
}
