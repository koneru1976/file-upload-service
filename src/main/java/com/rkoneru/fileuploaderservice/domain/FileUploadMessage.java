package com.rkoneru.fileuploaderservice.domain;

public class FileUploadMessage {

    private final String fileName;
    private final Double fileSize;

    public FileUploadMessage(String fileName, Double fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public Double getFileSize() {
        return fileSize;
    }
}
