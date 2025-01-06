package com.falabella.sales.files.infrastructure.in.rest.controllers;

import com.falabella.sales.files.application.ports.in.CreateOneFileServicePort;
import com.falabella.sales.files.domain.exceptions.FileDuplicatedException;
import com.falabella.sales.files.domain.models.File;
import com.falabella.sales.files.infrastructure.in.rest.dtos.FileCreateRequest;
import com.falabella.sales.files.infrastructure.in.rest.dtos.FileResponse;
import com.falabella.sales.files.infrastructure.in.rest.mappers.FileRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/files")
public class CreateOneFileRestController {
    private final CreateOneFileServicePort createOneFileServicePort;
    public CreateOneFileRestController(CreateOneFileServicePort createOneFileServicePort) {
        this.createOneFileServicePort = createOneFileServicePort;
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('Admin', 'Operator')")
    public ResponseEntity<FileResponse> createOneFile(
        @ModelAttribute @Valid FileCreateRequest fileCreateRequest
    ) throws IOException, FileDuplicatedException {
        File file = FileRestMapper.createRequestToDomain(fileCreateRequest);
        File savedFile = this.createOneFileServicePort.execute(fileCreateRequest.getDirectory(), file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fileId}").buildAndExpand(savedFile.getId()).toUri();
        return ResponseEntity.created(uri).body(FileRestMapper.domainToResponse(savedFile));
    }
}
