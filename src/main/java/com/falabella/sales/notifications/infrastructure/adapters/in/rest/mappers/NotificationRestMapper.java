package com.falabella.sales.notifications.infrastructure.adapters.in.rest.mappers;

import com.falabella.sales.commons.domain.models.Page;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.utils.IntegerUtils;

import com.falabella.sales.notifications.domain.models.Notification;
import com.falabella.sales.notifications.domain.models.NotificationFilters;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationCreateRequest;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationQueryRequest;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationResponse;
import com.falabella.sales.notifications.infrastructure.adapters.in.rest.dtos.NotificationUpdateRequest;

import java.util.List;

public class NotificationRestMapper {
    private NotificationRestMapper() {}
    public static NotificationResponse domainToResponse(Notification notification) {
        return NotificationResponse.builder()
            .id(notification.getId())
            .title(notification.getTitle())
            .message(notification.getMessage())
            .status(notification.getStatus())
            .createdAt(notification.getCreatedAt())
            .updatedAt(notification.getUpdatedAt())
            .userID(notification.getUserID())
            .build();
    }
    public static Notification createRequestToDomain(NotificationCreateRequest notificationCreateRequest) {
        return Notification.builder()
            .id(null)
            .title(notificationCreateRequest.getTitle())
            .message(notificationCreateRequest.getMessage())
            .status(notificationCreateRequest.getStatus())
            .createdAt(null)
            .updatedAt(null)
            .userID(notificationCreateRequest.getUserID())
            .build();
    }
    public static Notification updateRequestToDomain(Long notificationId, NotificationUpdateRequest notificationUpdateRequest) {
        return Notification.builder()
            .id(notificationId)
            .title(notificationUpdateRequest.getTitle())
            .message(notificationUpdateRequest.getMessage())
            .status(notificationUpdateRequest.getStatus())
            .userID(notificationUpdateRequest.getUserID())
            .build();
    }
    public static PaginationResultResponse<NotificationResponse> domainToPaginationResponse(PaginationResult<Notification> notificationPaginationResult) {
        return PaginationResultResponse.<NotificationResponse>builder()
            .totalItems(notificationPaginationResult.getTotalItems())
            .totalPages(notificationPaginationResult.getTotalPages())
            .currentPage(notificationPaginationResult.getCurrentPage())
            .pageSize(notificationPaginationResult.getPageSize())
            .hasNextPage(notificationPaginationResult.getHasNextPage())
            .items(domainListToReponseList(notificationPaginationResult.getItems()))
            .build();
    }
    public static NotificationFilters queryRequestToFilters(NotificationQueryRequest notificationQueryRequest) {
        return NotificationFilters.builder()
            .page(
                Page.builder()
                    .number(IntegerUtils.safeParseInteger(notificationQueryRequest.getPage(), 0))
                    .size(IntegerUtils.safeParseInteger(notificationQueryRequest.getSize(), 10))
                    .build()
            )
            .title(notificationQueryRequest.getTitle())
            .message(notificationQueryRequest.getMessage())
            .status(notificationQueryRequest.getStatus())
            .build();
    }
    private static List<NotificationResponse> domainListToReponseList(List<Notification> notifications) {
        return notifications.stream().map(NotificationRestMapper::domainToResponse).toList();
    }
}
