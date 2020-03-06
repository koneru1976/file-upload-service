package com.rkoneru.fileuploaderservice.controller;

import com.rkoneru.fileuploaderservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequestMapping("files")
@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.uploadFile(file);
        URI fileLocation = URI.create(String.format("/files/%s", file.getName()));
        return ResponseEntity.created(fileLocation).build();
    }
}
