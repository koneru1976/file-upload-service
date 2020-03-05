package com.rkoneru.fileuploaderservice.service;

import com.rkoneru.fileuploaderservice.vo.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

public class FakeFileService extends FileService {

    public FakeFileService() {
        super(null);
    }

    @Override
    public FileUploadResult saveMultipartFile(MultipartFile multipartFile) {
        return new FileUploadResult("test");
    }
}
