package com.rkoneru.fileuploaderservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import com.rkoneru.fileuploaderservice.MultipartFileMother;
import com.rkoneru.fileuploaderservice.exception.FileUploadException;
import com.rkoneru.fileuploaderservice.vo.FileUploadResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileServiceTest {

    @TempDir
    public Path temporaryFolder;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService(temporaryFolder);
    }

    @Test
    void successfulFileSaveShouldReturnTrue() throws IOException {
        FileUploadResult fileUploadResult = fileService.saveMultipartFile(MultipartFileMother.create());

        assertEquals("test", fileUploadResult.getFileName());
    }

    @Test
    void unsuccessfulFileSaveShouldReturnFalse() {
        Assertions.assertThrows(FileUploadException.class, () -> fileService.saveMultipartFile(null));
    }
}
