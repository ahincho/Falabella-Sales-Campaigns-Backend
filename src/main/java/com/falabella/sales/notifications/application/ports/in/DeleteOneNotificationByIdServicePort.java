package com.falabella.sales.notifications.application.ports.in;

import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;

public interface DeleteOneNotificationByIdServicePort {
    void execute(Long notificationId) throws NotificationNotFoundException;
}
