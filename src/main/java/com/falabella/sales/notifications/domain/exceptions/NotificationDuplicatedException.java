package com.falabella.sales.notifications.domain.exceptions;

public class NotificationDuplicatedException extends Exception {
    public NotificationDuplicatedException(String message) {
        super(message);
    }
}
