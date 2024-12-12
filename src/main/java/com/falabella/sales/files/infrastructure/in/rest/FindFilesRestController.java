package com.falabella.sales.files.infrastructure.in.rest;

import com.falabella.sales.files.application.ports.in.FindFilesServicePort;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FindFilesRestController {
    private final FindFilesServicePort findFilesServicePort;
    public FindFilesRestController(FindFilesServicePort findFilesServicePort) {
        this.findFilesServicePort = findFilesServicePort;
    }
    @GetMapping
    public ResponseEntity<List<String>> findFiles() {
        return ResponseEntity.ok(this.findFilesServicePort.execute());
    }
}
