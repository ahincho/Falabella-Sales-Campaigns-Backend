package com.falabella.sales.files.application.ports.in;

import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.domain.models.FileFilters;

import java.util.List;

public interface FindFilesServicePort {
    List<File> execute(String directory, FileFilters fileFilters);
}
