package com.rkoneru.fileuploaderservice.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rkoneru.fileuploaderservice.exception.FileUploadException;
import com.rkoneru.fileuploaderservice.vo.FileUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Path fileStorageLocation;

    @Autowired
    public FileService(Path fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
    }

    public FileUploadResult saveMultipartFile(MultipartFile multipartFile) {
        try {
            Files.write(Paths.get(getFileStorageLocation(), multipartFile.getName()), multipartFile.getBytes());
            return new FileUploadResult(multipartFile.getName());
        } catch (Exception e) {
            logger.error("Error saving file", e);
            throw new FileUploadException();
        }
    }

    private String getFileStorageLocation() {
        return fileStorageLocation.toString();
    }
}
