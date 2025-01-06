package com.falabella.sales.notifications.application.ports.in;

import com.falabella.sales.notifications.domain.exceptions.NotificationDuplicatedException;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;

public interface UpdateOneNotificationByIdServicePort {
    void execute(Long notificationId, Notification notification) throws NotificationNotFoundException, NotificationDuplicatedException;
}
