package com.poj.judge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poj.judge.dto.ExecutionResult;
import com.poj.judge.exception.DockerExecutionException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DockerRunner {
    private final String FILE_NAME_IN_CONTAINER = "Main";
    private final int DEFAULT_DOCKER_MEMORY_LIMIT = 512;

    public ExecutionResult run(File sourceCode, File inputFile, File submitResultDir, Language language, int timeLimit, int memoryLimit) {
        String dockerImage = language.getDockerImage();
        String command = getCommand(timeLimit, memoryLimit, language);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(
                "docker", "run", "--rm",
                "--memory", DEFAULT_DOCKER_MEMORY_LIMIT + "m",
                "-v", sourceCode.getAbsolutePath() + ":/app/" + FILE_NAME_IN_CONTAINER + language.getExtension(),
                "-v", inputFile.getAbsolutePath() + ":/app/input.txt",
                "-v", submitResultDir.getAbsolutePath() + ":/app/result",
                dockerImage,
                "/bin/sh", "-c",
                command
        );

        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            switch (exitCode) {
                case 0 :
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        File file = new File(submitResultDir.getAbsolutePath() + "/result.json");
                        ExecutionResult executionResult = objectMapper.readValue(file, ExecutionResult.class);
                        return executionResult;
                    } catch (Exception e) {
                        throw new DockerExecutionException("Failed to read result.json");
                    }
                case 137 :
                    return new ExecutionResult("Memory Error", 0, 0, "");
                case 124 :
                    return new ExecutionResult("Timeout Error", 0, 0, "");
                default :
                    return new ExecutionResult("Server Error", 0, 0, "");
            }
        } catch (IOException | InterruptedException exception) {
            throw new DockerExecutionException("Failed to execute docker command");
        }
    }

    private String getCommand(int timeLimit, int memoryLimit, Language language) {
        switch (language) {
            case CPP :
                return String.format("python3 CppRunner.py --timeout %d --memory %d", timeLimit, memoryLimit);
            case JAVA :
                return String.format("python3 JavaRunner.py --timeout %d --memory %d", timeLimit, memoryLimit);
            case PYTHON :
                return String.format("python3 PythonRunner.py --timeout %d --memory %d", timeLimit, memoryLimit);
            default:
                throw new DockerExecutionException("Invalid language");
        }
    }
}
