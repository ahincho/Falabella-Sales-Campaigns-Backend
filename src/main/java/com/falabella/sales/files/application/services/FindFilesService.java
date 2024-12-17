package com.falabella.sales.files.application.services;

import com.falabella.sales.files.application.ports.in.FindFilesServicePort;
import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.domain.models.FileFilters;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFilesService implements FindFilesServicePort {
    private final StoragePort storagePort;
    public FindFilesService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    @Override
    public List<File> execute(String directory, FileFilters fileFilters) {
        return this.storagePort.findFiles(directory, fileFilters);
    }
}
