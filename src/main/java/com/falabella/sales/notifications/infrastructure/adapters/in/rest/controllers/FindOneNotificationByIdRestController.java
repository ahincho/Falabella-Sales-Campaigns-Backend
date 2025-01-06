package com.falabella.sales.notifications.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.notifications.application.ports.in.FindOneNotificationByIdServicePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationNotFoundException;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationResponse;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.mappers.NotificationRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@PreAuthorize("hasAnyRole('Admin', 'Operator')")
public class FindOneNotificationByIdRestController {
    private final FindOneNotificationByIdServicePort findOneNotificationByIdServicePort;
    public FindOneNotificationByIdRestController(FindOneNotificationByIdServicePort findOneNotificationByIdServicePort) {
        this.findOneNotificationByIdServicePort = findOneNotificationByIdServicePort;
    }
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse> findOneNotificationById(@PathVariable Long notificationId) throws NotificationNotFoundException {
        Notification notification = this.findOneNotificationByIdServicePort.execute(notificationId);
        return ResponseEntity.ok(NotificationRestMapper.domainToResponse(notification));
    }
}
