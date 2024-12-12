package com.falabella.sales.files.application.services;

import com.falabella.sales.files.application.ports.in.FindFilesServicePort;
import com.falabella.sales.files.application.ports.out.StoragePort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFilesService implements FindFilesServicePort {
    public final StoragePort storagePort;
    public FindFilesService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    @Override
    public List<String> execute() {
        return this.storagePort.findFiles();
    }
}
