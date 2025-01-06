package com.falabella.sales.notifications.application.ports.in;

import com.falabella.sales.notifications.domain.exceptions.NotificationDuplicatedException;
import com.falabella.sales.notifications.domain.models.Notification;

public interface CreateOneNotificationServicePort {
    Notification execute(Notification notification) throws NotificationDuplicatedException;
}
