package com.poj.judge.dto;

import lombok.Data;

@Data
public class JudgeResponse {
    Result result;
    String message;

    public JudgeResponse(Result result, String message) {
        this.result = result;
        this.message = message;
    }
}
