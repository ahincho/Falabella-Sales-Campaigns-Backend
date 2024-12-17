package com.falabella.sales.files.infrastructure.in.rest.mappers;

import com.falabella.sales.files.domain.models.ContentType;
import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.infrastructure.in.rest.dtos.FileCreateRequest;
import com.falabella.sales.files.infrastructure.in.rest.dtos.FileResponse;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FileRestMapper {
    private FileRestMapper() {}
    public static File createRequestToDomain(FileCreateRequest fileCreateRequest) throws IOException {
        MultipartFile multipartFile = fileCreateRequest.getMultipartFile();
        String directory = fileCreateRequest.getDirectory();
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        String mimeType = Objects.requireNonNull(multipartFile.getContentType()).toLowerCase();
        ContentType contentType = ContentType.fromString(mimeType);
        return File.builder()
            .id(directory + "/" + fileName)
            .name(fileName)
            .size(multipartFile.getSize())
            .content(inputStream)
            .type(contentType)
            .build();
    }
    public static FileResponse domainToResponse(File file) {
        return FileResponse.builder()
            .id(file.getId())
            .name(file.getName())
            .size(file.getSize())
            .type(file.getType().name())
            .url(file.getUrl())
            .createdAt(file.getCreatedAt())
            .build();
    }
}
