package com.bane.clean.architecture.api.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static String getFileContents(String fileName) throws IOException {
        return Files.readString(new ClassPathResource(fileName).getFile().toPath());
    }
}
