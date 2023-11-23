package com.poj.judge.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OutputComparator {
    public boolean compare(String userOutput, String testOutput) {
        List<String> resultWords = Arrays.asList(userOutput.split("\\s+"));
        List<String> testCaseWords = Arrays.asList(testOutput.split("\\s+"));

        if (resultWords.size() != testCaseWords.size()) {
            return false;
        }

        for (int i = 0; i < resultWords.size(); i++) {
            if (!resultWords.get(i).equals(testCaseWords.get(i))) {
                return false;
            }
        }

        return true;
    }
}
