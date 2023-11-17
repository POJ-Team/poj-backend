package com.poj.judge.dto;

import com.poj.judge.service.Language;
import lombok.Data;

import java.util.List;

@Data
public class JudgeRequest {
    private String sourceCode;
    private Language language;
    private Integer limitTimeInMS;
    private Integer limitMemoryInKB;
    private List<TestCase> testCases;

    @Data
    public static class TestCase {
        private String input;
        private String output;

        public TestCase(String input, String output) {
            this.input = input;
            this.output = output;
        }
    }

    public JudgeRequest(String sourceCode, Language language, Integer limitTimeInMS, Integer limitMemoryInKB, List<TestCase> testCases) {
        this.sourceCode = sourceCode;
        this.language = language;
        this.limitTimeInMS = limitTimeInMS;
        this.limitMemoryInKB = limitMemoryInKB;
        this.testCases = testCases;
    }
}
