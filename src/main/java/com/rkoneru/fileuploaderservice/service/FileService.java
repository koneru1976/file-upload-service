package com.rkoneru.fileuploaderservice.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rkoneru.fileuploaderservice.domain.FileUploadMessage;
import com.rkoneru.fileuploaderservice.exception.FileUploadException;
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
        FileUploadMessage fileUploadMessage = new FileUploadMessage(multipartFile.getName(), 5.0);
        simpMessagingTemplate.convertAndSend(FILE_UPLOADS_TOPIC, fileUploadMessage);
    }

    private String getFileStorageLocation() {
        return fileStorageLocation.toString();
    }
}
