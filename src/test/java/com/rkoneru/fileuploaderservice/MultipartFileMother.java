package com.rkoneru.fileuploaderservice;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileMother {
    private MultipartFileMother() {
    }

    public static MultipartFile create() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("rkoneru.doc");
        MockMultipartFile multipartFile = new MockMultipartFile(classPathResource.getFilename(), classPathResource.getInputStream());
        return multipartFile;
    }
}
