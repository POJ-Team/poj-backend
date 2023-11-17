package com.poj.judge.dto;

import lombok.Data;

@Data
public class ExecutionResult {
    String error;
    double executionTime;
    int memoryUsage;
    String output;

    public ExecutionResult() {
    }

    public ExecutionResult(String error, double executionTime, int memoryUsage, String output) {
        this.error = error;
        this.executionTime = executionTime;
        this.memoryUsage = memoryUsage;
        this.output = output;
    }
}
