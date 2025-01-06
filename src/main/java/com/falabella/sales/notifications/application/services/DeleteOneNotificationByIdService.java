package com.falabella.sales.notifications.application.services;

import com.falabella.sales.notifications.application.ports.in.DeleteOneNotificationByIdServicePort;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class DeleteOneNotificationByIdService implements DeleteOneNotificationByIdServicePort {
    private final NotificationPersistencePort notificationPersistencePort;
    public DeleteOneNotificationByIdService(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }
    @Override
    public void execute(Long notificationId) throws NotificationNotFoundException {
        if (!this.notificationPersistencePort.existsOneNotificationById(notificationId)) {
            throw new NotificationNotFoundException("Notification with id '" + notificationId + "' not found");
        }
        this.notificationPersistencePort.deleteOneNotificationById(notificationId);
    }
}
