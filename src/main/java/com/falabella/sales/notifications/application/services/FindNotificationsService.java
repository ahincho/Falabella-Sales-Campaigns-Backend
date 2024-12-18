package com.falabella.sales.notifications.application.services;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.notifications.application.ports.in.FindNotificationsServicePort;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;

import org.springframework.stereotype.Service;

@Service
public class FindNotificationsService implements FindNotificationsServicePort {
    private final NotificationPersistencePort notificationPersistencePort;
    public FindNotificationsService(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }
    @Override
    public PaginationResult<Notification> execute(NotificationFilters notificationFilters) {
        return this.notificationPersistencePort.findNotifications(notificationFilters);
    }
}
