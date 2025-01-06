package com.falabella.sales.notifications.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.notifications.application.ports.in.CreateOneNotificationServicePort;
import com.falabella.sales.notifications.domain.exceptions.NotificationDuplicatedException;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationCreateRequest;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationResponse;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.mappers.NotificationRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/notifications")
public class CreateOneNotificationRestController {
    private final CreateOneNotificationServicePort createOneNotificationServicePort;
    public CreateOneNotificationRestController(CreateOneNotificationServicePort createOneNotificationServicePort) {
        this.createOneNotificationServicePort = createOneNotificationServicePort;
    }
    @PostMapping
    public ResponseEntity<NotificationResponse> createOneNotification(
        @RequestBody @Valid NotificationCreateRequest notificationCreateRequest
    ) throws NotificationDuplicatedException {
        // Ejemplo de payload inferido:
        // {
        //   "title": "New Sale",
        //   "message": "Don't miss our new sale!",
        //   "userID": "12345"
        // }
        Notification notification = NotificationRestMapper.createRequestToDomain(notificationCreateRequest);
        Notification savedNotification = this.createOneNotificationServicePort.execute(notification);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{notificationId}").buildAndExpand(savedNotification.getId()).toUri();
        return ResponseEntity.created(uri).body(NotificationRestMapper.domainToResponse(savedNotification));
    }
}
