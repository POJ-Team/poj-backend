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
public class SubmitFileCreator {
    public File createTempSourceFile(String sourceCode, Language language, Path submitDirPath) {
        File tempSourceFile = null;
        try {
            tempSourceFile = File.createTempFile("Main", language.getExtension(), submitDirPath.toFile());
        } catch (IOException e) {
            String message = "Failed to create temp file";
            log.error(message);
            throw new FileCreateException(message);
        }

        try {
            Files.write(tempSourceFile.toPath(), sourceCode.getBytes());
        } catch (IOException e) {
            String message = "Failed to write source code to temp file";
            log.error(message);
            throw new FileCreateException(message);
        }

        return tempSourceFile;
    }

    public File createTempTextFile(String inputText, Path submitDirPath) {
        File tempTextFile = null;
        try {
            tempTextFile = File.createTempFile("input", ".txt", submitDirPath.toFile());
        } catch (IOException e) {
            String message = "Failed to create temp file";
            log.error(message);
            throw new FileCreateException(message);
        }
        try {
            Files.write(tempTextFile.toPath(), inputText.getBytes());
        } catch (IOException e) {
            String message = "Failed to write input text to temp file";
            log.error(message);
            throw new FileCreateException(message);
        }
        return tempTextFile;
    }
}
