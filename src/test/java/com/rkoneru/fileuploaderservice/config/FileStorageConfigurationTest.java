package com.rkoneru.fileuploaderservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FileStorageConfigurationTest {

    @Test
    void shouldReturnFileStorageLocation() {
        Path fileStorageLocation = new FileStorageConfiguration().fileStorageLocation();

        assertEquals(System.getProperty("java.io.tmpdir"), fileStorageLocation.toString() + File.separator);
    }
}
