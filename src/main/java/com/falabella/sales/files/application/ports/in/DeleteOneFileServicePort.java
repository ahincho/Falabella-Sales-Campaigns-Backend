package com.falabella.sales.files.application.ports.in;

import com.falabella.sales.files.domain.exceptions.FileNotFoundException;

public interface DeleteOneFileServicePort {
    void execute(String directory, String fileName) throws FileNotFoundException;
}
