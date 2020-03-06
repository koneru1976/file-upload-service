package com.rkoneru.fileuploaderservice.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rkoneru.fileuploaderservice.domain.FileUploadMessage;
import com.rkoneru.fileuploaderservice.exception.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public static final String FILE_UPLOADS_TOPIC = "/topics/file-uploads";


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Path fileStorageLocation;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public FileService(Path fileStorageLocation, SimpMessagingTemplate simpMessagingTemplate) {
        this.fileStorageLocation = fileStorageLocation;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        try {
            Files.write(Paths.get(getFileStorageLocation(), multipartFile.getName()), multipartFile.getBytes());
            sendFileUploadMessageToWebSocket(multipartFile);
            return true;
        } catch (Exception e) {
            logger.error("Error saving file", e);
            throw new FileUploadException();
        }
    }

    private void sendFileUploadMessageToWebSocket(MultipartFile multipartFile) {
        double fileSize = fileSize(multipartFile.getName());
        FileUploadMessage fileUploadMessage = new FileUploadMessage(multipartFile.getName(), fileSize);
        simpMessagingTemplate.convertAndSend(FILE_UPLOADS_TOPIC, fileUploadMessage);
    }

    private double fileSize(String fileName) {
        try {
            File file = new File(getFileStorageLocation() + File.separator + fileName);
            return FileUtils.sizeOf(file);
        } catch (Exception ex) {
            logger.error("Error calculation file size", ex);
        }
        return 0;

    }

    private String getFileStorageLocation() {
        return fileStorageLocation.toString();
    }
}
