package com.poj.judge.service;

import com.poj.judge.exception.FileCreateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class SubmitDirService {
    public File createDir(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                String message = "Failed to create directory : " + path;
                log.error(message);
                throw new FileCreateException(message);
            }
        }
        return path.toFile();
    }

    public void deleteDir(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDir(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        directory.delete();
    }
}
