package com.falabella.sales.files.application.ports.out;

import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.domain.models.FileFilters;

import java.util.List;
import java.util.Optional;

public interface StoragePort {
    File createOneFile(String directory, File file);
    List<File> findFiles(String directory, FileFilters fileFilters);
    Optional<File> findOneFile(String directory, String fileName);
    Boolean existsOneFileByName(String directory, String fileName);
    File updateOneFile(String directory, String fileName, File file);
    void deleteOneFile(String directory, String fileName);
}
