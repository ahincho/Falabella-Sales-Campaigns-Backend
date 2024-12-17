package com.falabella.sales.files.infrastructure.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileCreateRequest {
    @NotBlank(message = "Directory name is required")
    private String directory;
    @NotNull(message = "Multipart file is required")
    private MultipartFile multipartFile;
}
