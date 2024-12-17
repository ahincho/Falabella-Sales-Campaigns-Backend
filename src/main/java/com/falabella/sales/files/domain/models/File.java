package com.falabella.sales.files.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private String id;
    private String name;
    private Long size;
    private InputStream content;
    private ContentType type;
    private String url;
    private LocalDateTime createdAt;
}
