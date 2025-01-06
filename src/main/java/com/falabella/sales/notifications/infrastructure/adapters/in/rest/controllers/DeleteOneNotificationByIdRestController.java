package com.falabella.sales.notifications.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.notifications.application.ports.in.DeleteOneNotificationByIdServicePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@PreAuthorize("hasRole('Admin')")
public class DeleteOneNotificationByIdRestController {
    private final DeleteOneNotificationByIdServicePort deleteOneNotificationByIdServicePort;
    public DeleteOneNotificationByIdRestController(DeleteOneNotificationByIdServicePort deleteOneNotificationByIdServicePort) {
        this.deleteOneNotificationByIdServicePort = deleteOneNotificationByIdServicePort;
    }
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteOneNotificationById(@PathVariable Long notificationId) throws NotificationNotFoundException {
        this.deleteOneNotificationByIdServicePort.execute(notificationId);
        return ResponseEntity.noContent().build();
    }
}
