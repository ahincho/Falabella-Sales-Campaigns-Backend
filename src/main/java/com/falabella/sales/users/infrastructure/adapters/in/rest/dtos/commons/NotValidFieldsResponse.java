package com.falabella.sales.users.infrastructure.adapters.in.rest.dtos.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotValidFieldsResponse {
    private String path;
    private String method;
    private Integer statusCode;
    private String statusDescription;
    private List<NotValidField> notValidFields;
}