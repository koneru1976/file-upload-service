package com.rkoneru.fileuploaderservice.domain;

public class FileUploadMessage {

    private final String fileName;
    private final Double fileSizeInMegaBytes;

    public FileUploadMessage(String fileName, Double fileSizeInMegaBytes) {
        this.fileName = fileName;
        this.fileSizeInMegaBytes = fileSizeInMegaBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public Double getFileSizeInMegaBytes() {
        return fileSizeInMegaBytes;
    }
}
