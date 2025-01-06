package com.falabella.sales.files.application.ports.in;

import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;

public interface CreateOneFileServicePort {
    File execute(String directory, File file) throws FileDuplicatedException;
}
