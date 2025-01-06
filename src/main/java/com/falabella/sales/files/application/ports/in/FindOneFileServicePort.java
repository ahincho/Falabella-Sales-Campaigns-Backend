package com.falabella.sales.files.application.ports.in;

import com.falabella.sales.files.domain.exceptions.FileNotFoundException;
import com.falabella.sales.files.domain.models.File;

public interface FindOneFileServicePort {
    File execute(String directory, String fileName) throws FileNotFoundException;
}
