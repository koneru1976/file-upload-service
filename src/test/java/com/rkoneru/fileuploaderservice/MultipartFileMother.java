package com.rkoneru.fileuploaderservice;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileMother {
    private MultipartFileMother() {
    }

    public static MultipartFile create() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.pdf");
        MockMultipartFile multipartFile = new MockMultipartFile("test", classPathResource.getInputStream());
        return multipartFile;
    }
}
