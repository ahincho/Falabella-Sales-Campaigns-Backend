package com.falabella.sales.files.application.services;

import com.falabella.sales.files.application.ports.in.DeleteOneFileServicePort;
import com.falabella.sales.files.application.ports.out.StoragePort;
import com.falabella.sales.files.domain.exceptions.FileNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class DeleteOneFileService implements DeleteOneFileServicePort {
    private final StoragePort storagePort;
    public DeleteOneFileService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    @Override
    public void execute(String directory, String fileName) throws FileNotFoundException {
        if (!this.storagePort.existsOneFileByName(directory, fileName)) {
            throw new FileNotFoundException("File with name '" + fileName + "' does not exist");
        }
        this.storagePort.deleteOneFile(directory, fileName);
    }
}
