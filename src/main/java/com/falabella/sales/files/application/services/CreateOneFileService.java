package com.falabella.sales.files.application.services;

import com.falabella.sales.files.application.ports.in.CreateOneFileServicePort;
import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;

import org.springframework.stereotype.Service;

@Service
public class CreateOneFileService implements CreateOneFileServicePort {
    private final StoragePort storagePort;
    public CreateOneFileService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    @Override
    public File execute(String directory, File file) throws FileDuplicatedException {
        if (this.storagePort.existsOneFileByName(directory, file.getName())) {
            throw new FileDuplicatedException("File with name '" + file.getName() + "' already exists");
        }
        return this.storagePort.createOneFile(directory, file);
    }
}
