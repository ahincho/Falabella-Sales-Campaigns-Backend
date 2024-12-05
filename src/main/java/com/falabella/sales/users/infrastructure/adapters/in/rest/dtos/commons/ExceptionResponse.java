package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String path;
    private String method;
    private Integer statusCode;
    private String statusDescription;
    private String message;
}
