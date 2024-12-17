package com.falabella.sales.files.infrastructure.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String id;
    private String name;
    private Long size;
    private String type;
    private String url;
    private LocalDateTime createdAt;
}
