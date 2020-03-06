package com.rkoneru.fileuploaderservice.service;

import org.springframework.web.multipart.MultipartFile;

public class FakeFileService extends FileService {

    public FakeFileService() {
        super(null, null);
    }

    @Override
    public boolean uploadFile(MultipartFile multipartFile) {
        return true;
    }
}
