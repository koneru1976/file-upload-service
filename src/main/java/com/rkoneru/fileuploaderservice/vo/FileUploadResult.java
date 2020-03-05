package com.rkoneru.fileuploaderservice.vo;

public class FileUploadResult {
    private final String fileName;

    public FileUploadResult(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
