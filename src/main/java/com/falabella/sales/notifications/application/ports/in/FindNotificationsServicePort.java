package com.falabella.sales.notifications.application.ports.in;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;

public interface FindNotificationsServicePort {
    PaginationResult<Notification> execute(NotificationFilters notificationFilters);
}
