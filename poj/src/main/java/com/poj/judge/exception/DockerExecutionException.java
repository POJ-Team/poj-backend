package com.poj.judge.exception;

public class DockerExecutionException extends RuntimeException{
    public DockerExecutionException(String message) {
        super(message);
    }
    public DockerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
