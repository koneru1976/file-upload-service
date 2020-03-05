package com.rkoneru.fileuploaderservice.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfiguration {

    @Bean
    public Path fileStorageLocation() {
        return Paths.get(System.getProperty("java.io.tmpdir"));
    }
}
