package com.falabella.sales.notifications.application.services;

import com.falabella.sales.notifications.application.ports.in.UpdateOneNotificationByIdServicePort;
import com.falabella.sales.notifications.application.ports.out.NotificationPersistencePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOneNotificationByIdService implements UpdateOneNotificationByIdServicePort {
    private final NotificationPersistencePort notificationPersistencePort;
    public UpdateOneNotificationByIdService(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }
    @Override
    public void execute(Long notificationId, Notification notification) throws NotificationNotFoundException {
        Optional<Notification> optionalExistingNotification = this.notificationPersistencePort.findOneNotificationById(notificationId);
        if (optionalExistingNotification.isEmpty()) {
            throw new NotificationNotFoundException("Notification with id '" + notificationId + "' not found");
        }
        Notification existingNotification = optionalExistingNotification.get();
        if (notification.getTitle() != null) {
            existingNotification.setTitle(notification.getTitle().toLowerCase());
        }
        if (notification.getMessage() != null) {
            existingNotification.setMessage(notification.getMessage().toLowerCase());
        }
        if (notification.getStatus() != null) {
            existingNotification.setStatus(notification.getStatus());
        }
        notification.setCreatedAt(existingNotification.getCreatedAt());
        this.notificationPersistencePort.updateOneNotificationById(notificationId, notification);
    }
}
