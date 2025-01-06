package com.falabella.sales.notifications.domain.exceptions;

public class NotificationNotFoundException extends Exception {
    public NotificationNotFoundException(String message) {
        super(message);
    }
}
