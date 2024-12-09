package com.falabella.sales.commons.infrastructure.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResultResponse<T> {
    private Long totalItems;
    private Integer totalPages;
    private Integer currentPage;
    private Integer pageSize;
    private Boolean hasNextPage;
    private List<T> items;
}
