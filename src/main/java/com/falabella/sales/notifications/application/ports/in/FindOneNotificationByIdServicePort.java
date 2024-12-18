package com.falabella.sales.notifications.application.ports.in;

import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;

public interface FindOneNotificationByIdServicePort {
    Notification execute(Long notificationId) throws NotificationNotFoundException;
}
