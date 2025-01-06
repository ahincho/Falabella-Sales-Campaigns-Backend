package com.falabella.sales.notifications.application.services;

import com.falabella.sales.notifications.application.ports.in.CreateOneNotificationServicePort;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.models.Notification;

import org.springframework.stereotype.Service;

@Service
public class CreateOneNotificationService implements CreateOneNotificationServicePort {
    private final NotificationPersistencePort notificationPersistencePort;
    public CreateOneNotificationService (
        NotificationPersistencePort notificationPersistencePort
    ) {
        this.notificationPersistencePort = notificationPersistencePort;
    }
    @Override
    public Notification execute(Notification notification) {
        notification.setTitle(notification.getTitle().toLowerCase());
        notification.setMessage(notification.getMessage().toLowerCase());
        return this.notificationPersistencePort.createOneNotification(notification);
    }
}
