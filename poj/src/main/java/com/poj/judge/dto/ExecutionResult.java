package com.poj.judge.dto;

import lombok.Data;

@Data
public class ExecutionResult {
    String error;
    int executionTime;
    int memoryUsage;
    String output;

    public ExecutionResult(String error, int executionTime, int memoryUsage, String output) {
        this.error = error;
        this.executionTime = executionTime;
        this.memoryUsage = memoryUsage;
        this.output = output;
    }
}
