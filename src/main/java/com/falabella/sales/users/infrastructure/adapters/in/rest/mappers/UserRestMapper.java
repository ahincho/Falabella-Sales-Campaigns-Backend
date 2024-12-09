package com.falabella.sales.users.infrastructure.adapters.in.rest.mappers;

import com.falabella.sales.commons.domain.models.Page;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.commons.infrastructure.adapters.in.rest.utils.IntegerUtils;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.User;
import com.falabella.sales.users.domain.models.UserFilters;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserCreateRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserQueryRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.UserUpdateRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRestMapper {
    private UserRestMapper() {}
    public static UserResponse domainToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .username(user.getUsername())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .roles(domainRoleSetToResponseRoleSet(user.getRoles()))
            .build();
    }
    public static User createRequestToDomain(UserCreateRequest userCreateRequest) {
        return User.builder()
            .id(null)
            .firstname(userCreateRequest.getFirstname())
            .lastname(userCreateRequest.getLastname())
            .username(userCreateRequest.getUsername())
            .email(userCreateRequest.getEmail())
            .createdAt(null)
            .updatedAt(null)
            .build();
    }
    public static User updateRequestToDomain(Long userId, UserUpdateRequest userUpdateRequest) {
        return User.builder()
            .id(userId)
            .firstname(userUpdateRequest.getFirstname())
            .lastname(userUpdateRequest.getLastname())
            .username(userUpdateRequest.getUsername())
            .email(userUpdateRequest.getEmail())
            .build();
    }
    public static PaginationResultResponse<UserResponse> domainToPaginationResponse(PaginationResult<User> userPaginationResult) {
        return PaginationResultResponse.<UserResponse>builder()
            .totalItems(userPaginationResult.getTotalItems())
            .totalPages(userPaginationResult.getTotalPages())
            .currentPage(userPaginationResult.getCurrentPage())
            .pageSize(userPaginationResult.getPageSize())
            .hasNextPage(userPaginationResult.getHasNextPage())
            .items(domainListToReponseList(userPaginationResult.getItems()))
            .build();
    }
    public static UserFilters queryRequestToFilters(UserQueryRequest userQueryRequest) {
        return UserFilters.builder()
            .page(
                Page.builder()
                    .number(IntegerUtils.safeParseInteger(userQueryRequest.getPage(), 0))
                    .size(IntegerUtils.safeParseInteger(userQueryRequest.getSize(), 10))
                    .build()
            )
            .firstname(userQueryRequest.getFirstname())
            .lastname(userQueryRequest.getLastname())
            .username(userQueryRequest.getUsername())
            .build();
    }
    private static Set<String> domainRoleSetToResponseRoleSet(Set<Role> roles) {
        return Optional.ofNullable(roles)
            .orElse(Collections.emptySet())
            .stream()
            .map(Role::getName)
            .collect(Collectors.toSet());
    }
    private static List<UserResponse> domainListToReponseList(List<User> users) {
        return users.stream().map(UserRestMapper::domainToResponse).toList();
    }
}
