package com.falabella.sales.users.infrastructure.adapters.in.rest.mappers;

import com.falabella.sales.commons.domain.models.Page;
import com.falabella.sales.commons.domain.models.PaginationResult;
import com.falabella.sales.commons.infrastructure.in.rest.dtos.PaginationResultResponse;
import com.falabella.sales.commons.infrastructure.in.rest.utils.IntegerUtils;
import com.falabella.sales.users.domain.models.Role;
import com.falabella.sales.users.domain.models.RoleFilters;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.RoleQueryRequest;
import com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.RoleResponse;

import java.util.List;

public class RoleRestMapper {
    private RoleRestMapper() {}
    public static RoleResponse domainToResponse(Role role) {
        return RoleResponse.builder()
            .id(role.getId())
            .name(role.getName())
            .description(role.getDescription())
            .createdAt(role.getCreatedAt())
            .updatedAt(role.getUpdatedAt())
            .build();
    }
    public static PaginationResultResponse<RoleResponse> domainToPaginationResponse(PaginationResult<Role> rolePaginationResult) {
        return PaginationResultResponse.<RoleResponse>builder()
            .totalItems(rolePaginationResult.getTotalItems())
            .totalPages(rolePaginationResult.getTotalPages())
            .currentPage(rolePaginationResult.getCurrentPage())
            .pageSize(rolePaginationResult.getPageSize())
            .hasNextPage(rolePaginationResult.getHasNextPage())
            .items(domainListToReponseList(rolePaginationResult.getItems()))
            .build();
    }
    public static RoleFilters queryRequestToFilters(RoleQueryRequest roleQueryRequest) {
        return RoleFilters.builder()
            .page(
                Page.builder()
                    .number(IntegerUtils.safeParseInteger(roleQueryRequest.getPage(), 0))
                    .size(IntegerUtils.safeParseInteger(roleQueryRequest.getSize(), 10))
                    .build()
            )
            .name(roleQueryRequest.getName())
            .build();
    }
    private static List<RoleResponse> domainListToReponseList(List<Role> roles) {
        return roles.stream().map(RoleRestMapper::domainToResponse).toList();
    }
}
