package com.rkoneru.fileuploadservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.rkoneru.fileuploaderservice.FileUploaderServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = FileUploaderServiceApplication.class)
public class FileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successfulFileUploadShouldReturn201() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.multipart("/files").file(createMockMultipartFile());
        MvcResult mvcResult = mockMvc.perform(rb).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    private MockMultipartFile createMockMultipartFile() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.pdf");
        return new MockMultipartFile("file", classPathResource.getInputStream());
    }
}
