package com.falabella.sales.files.infrastructure.out.storage.mappers;

import com.falabella.sales.files.domain.models.ContentType;
import com.falabella.sales.files.domain.models.File;

import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AwsS3Mapper {
    private AwsS3Mapper() {}
    public static File s3ToDomain(String cloudfrontEndpoint, HeadObjectResponse headObjectResponse, String key) {
        ContentType contentTypeEnum = ContentType.fromString(headObjectResponse.contentType().toLowerCase()); // Usamos fromString en lugar de valueOf
        return File.builder()
            .id(key)
            .name(key)
            .size(headObjectResponse.contentLength())
            .type(contentTypeEnum)
            .url(generateFileUrl(cloudfrontEndpoint, key))
            .createdAt(instantToLocalDateTime(headObjectResponse.lastModified()))
            .build();
    }
    private static LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
    }
    private static String generateFileUrl(String cloudfrontEndpoint, String key) {
        return String.format("%s/%s", cloudfrontEndpoint, key);
    }
}
