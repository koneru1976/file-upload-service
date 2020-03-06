package com.rkoneru.fileuploaderservice.service;

import com.rkoneru.fileuploaderservice.MultipartFileMother;
import com.rkoneru.fileuploaderservice.exception.FileUploadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class FileServiceTest {

    @TempDir
    public Path temporaryFolder;
    private FileService fileService;

    private SimpMessagingTemplate mockSimpMessagingTemplate = mock(SimpMessagingTemplate.class);

    @BeforeEach
    void setUp() {
        fileService = new FileService(temporaryFolder, mockSimpMessagingTemplate);
    }

    @Test
    void successfulFileUploadShouldReturnTrue() throws IOException {
        boolean isFileUploadSuccess = fileService.uploadFile(MultipartFileMother.create());

        assertTrue(isFileUploadSuccess);
    }

    @Test
    void unsuccessfulFileUploadShouldReturnFalse() {
        Assertions.assertThrows(FileUploadException.class, () -> fileService.uploadFile(null));
    }
}
