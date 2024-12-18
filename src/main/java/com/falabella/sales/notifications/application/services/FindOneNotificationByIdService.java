package com.falabella.sales.notifications.application.services;

import com.falabella.sales.notifications.application.ports.in.FindOneNotificationByIdServicePort;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneNotificationByIdService implements FindOneNotificationByIdServicePort {
    private final NotificationPersistencePort notificationPersistencePort;
    public FindOneNotificationByIdService(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }
    @Override
    public Notification execute(Long notificationId) throws NotificationNotFoundException {
        Optional<Notification> notification = this.notificationPersistencePort.findOneNotificationById(notificationId);
        if (notification.isEmpty()) {
            throw new NotificationNotFoundException("Notification with id '" + notificationId + "' not found");
        }
        return notification.get();
    }
}
