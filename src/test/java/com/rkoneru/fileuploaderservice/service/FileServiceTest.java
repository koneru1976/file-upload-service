package com.rkoneru.fileuploaderservice.service;

import com.rkoneru.fileuploaderservice.MultipartFileMother;
import com.rkoneru.fileuploaderservice.domain.FileUploadMessage;
import com.rkoneru.fileuploaderservice.exception.FileUploadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @TempDir
    public Path temporaryFolder;

    @Captor
    private ArgumentCaptor<String > destinationTopicCaptor;

    @Captor
    private ArgumentCaptor<FileUploadMessage> fileUploadMessageCaptor;

    private FileService fileService;

    @Mock
    private SimpMessagingTemplate mockSimpMessagingTemplate;

    @BeforeEach
    void setUp() {
        fileService = new FileService(temporaryFolder, mockSimpMessagingTemplate);
    }

    @Test
    void successfulFileUploadShouldReturnTrue() throws IOException {
        boolean isFileUploadSuccess = fileService.uploadFile(MultipartFileMother.create());

        assertTrue(isFileUploadSuccess);
    }

    @Test
    void successfulFileUploadShouldSentFileUploadMessageToCorrectTopic() throws IOException {
        fileService.uploadFile(MultipartFileMother.create());

        verify(mockSimpMessagingTemplate).convertAndSend(destinationTopicCaptor.capture(), fileUploadMessageCaptor.capture());

        assertEquals("/topics/file-uploads", destinationTopicCaptor.getValue());
    }

    @Test
    void successfulFileUploadShouldSentFileUploadMessageWithFileNameAndSize() throws IOException {
        fileService.uploadFile(MultipartFileMother.create());

        verify(mockSimpMessagingTemplate).convertAndSend(destinationTopicCaptor.capture(), fileUploadMessageCaptor.capture());

        assertEquals("rkoneru.doc", fileUploadMessageCaptor.getValue().getFileName());
        assertEquals(71168.0, fileUploadMessageCaptor.getValue().getFileSize());
    }

    @Test
    void unsuccessfulFileUploadShouldReturnFalse() {
        Assertions.assertThrows(FileUploadException.class, () -> fileService.uploadFile(null));
    }
}
