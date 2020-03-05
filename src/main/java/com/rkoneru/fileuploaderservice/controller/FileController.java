package com.rkoneru.fileuploaderservice.controller;

import java.net.URI;

import com.rkoneru.fileuploaderservice.service.FileService;
import com.rkoneru.fileuploaderservice.vo.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        FileUploadResult fileUploadResult = fileService.saveMultipartFile(file);
        URI fileLocation = URI.create(fileUploadResult.getFileName());
        return ResponseEntity.created(fileLocation).body(fileUploadResult);
    }
}
