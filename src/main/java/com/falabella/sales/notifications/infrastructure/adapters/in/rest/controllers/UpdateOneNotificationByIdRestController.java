package com.falabella.sales.notifications.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.notifications.application.ports.in.UpdateOneNotificationByIdServicePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationDuplicatedException;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationUpdateRequest;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.mappers.NotificationRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@PreAuthorize("hasRole('Admin')")
public class UpdateOneNotificationByIdRestController {
    private final UpdateOneNotificationByIdServicePort updateOneNotificationByIdServicePort;
    public UpdateOneNotificationByIdRestController(UpdateOneNotificationByIdServicePort updateOneNotificationByIdServicePort) {
        this.updateOneNotificationByIdServicePort = updateOneNotificationByIdServicePort;
    }
    @PatchMapping("/{notificationId}")
    public ResponseEntity<Void> updateOneNotificationById(
        @PathVariable Long notificationId,
        @RequestBody @Valid NotificationUpdateRequest notificationUpdateRequest
    ) throws NotificationNotFoundException, NotificationDuplicatedException {
        Notification notification = NotificationRestMapper.updateRequestToDomain(notificationId, notificationUpdateRequest);
        this.updateOneNotificationByIdServicePort.execute(notificationId, notification);
        return ResponseEntity.noContent().build();
    }
}
