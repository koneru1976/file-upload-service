package com.rkoneru.fileuploaderservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.rkoneru.fileuploaderservice.service.FakeFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

class FileControllerTest {

    private FileController fileController;
    private MockMultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.pdf");
        file = new MockMultipartFile("test", classPathResource.getInputStream());

        fileController = new FileController(new FakeFileService());
    }

    @Test
    void successfulFileUploadShouldReturnResponseEntityWith201StatusCode() {
        ResponseEntity responseEntity = fileController.uploadFile(file);

        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
