package com.falabella.sales.files.application.services;

import com.falabella.sales.files.application.ports.in.FindOneFileServicePort;
import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.exceptions.FileNotFoundException;
import com.falabella.sales.files.domain.models.File;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneFileService implements FindOneFileServicePort {
    private final StoragePort storagePort;
    public FindOneFileService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    @Override
    public File execute(String directory, String fileName) throws FileNotFoundException {
        Optional<File> optionalFile = this.storagePort.findOneFile(directory, fileName);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException("File with name '" + fileName + "' not found");
        }
        return optionalFile.get();
    }
}
