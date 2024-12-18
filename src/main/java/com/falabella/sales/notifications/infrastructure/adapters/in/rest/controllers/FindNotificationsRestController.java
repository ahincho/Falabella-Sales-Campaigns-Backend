package com.falabella.sales.notifications.infrastructure.adapters.in.rest.controllers;

import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.notifications.application.ports.in.FindNotificationsServicePort;
import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationQueryRequest;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationResponse;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.mappers.NotificationRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@PreAuthorize("hasAnyRole('Admin', 'Operator')")
public class FindNotificationsRestController {
    private final FindNotificationsServicePort findNotificationsServicePort;
    public FindNotificationsRestController(FindNotificationsServicePort findNotificationsServicePort) {
        this.findNotificationsServicePort = findNotificationsServicePort;
    }
    @GetMapping
    public ResponseEntity<PaginationResultResponse<NotificationResponse>> findNotifications(
        @ModelAttribute @Valid NotificationQueryRequest notificationQueryRequest
    ) {
        NotificationFilters notificationFilters = NotificationRestMapper.queryRequestToFilters(notificationQueryRequest);
        PaginationResult<Notification> notificationPage = this.findNotificationsServicePort.execute(notificationFilters);
        if (notificationPage.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(NotificationRestMapper.domainToPaginationResponse(notificationPage));
    }
}
