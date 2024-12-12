package com.falabella.sales.files.application.ports.out;

import com.falabella.sales.files.domain.models.ContentType;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface StoragePort {
    String createOneFile(String fileName, InputStream inputStream, ContentType contentType);
    List<String> findFiles();
    Optional<InputStream> getOneFile(String fileName);
    void updateOneFile(String fileName, InputStream inputStream, ContentType contentType);
    void deleteOneFile(String fileName);
}
