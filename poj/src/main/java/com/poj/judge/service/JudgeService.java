package com.poj.judge.service;

import com.poj.judge.dto.ExecutionResult;
import com.poj.judge.dto.JudgeRequest;
import com.poj.judge.dto.JudgeResponse;
import com.poj.judge.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JudgeService {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String USER_SUBMIT_DIR = "src/main/java/com/poj/user_submit";

    private final SubmitFileCreator submitFileCreator;
    private final SubmitDirService submitDirService;
    private final DockerRunner dockerRunner;
    private final OutputComparator outputComparator;
    private Path getSubmitDirPath(String submitId) {
        return Path.of(CURRENT_DIRECTORY, USER_SUBMIT_DIR, submitId);
    }

    public JudgeResponse judge(JudgeRequest judgeRequest) {
        String submitId = UUID.randomUUID().toString();

        Path submitDirPath = getSubmitDirPath(submitId);
        File submitDir = submitDirService.createDir(submitDirPath);
        File submitResultDir = submitDirService.createDir(Path.of(submitDirPath.toString(), "result"));
        File tempSourceFile = submitFileCreator.createTempSourceFile(
                judgeRequest.getSourceCode(), judgeRequest.getLanguage(), submitDirPath);

        for (JudgeRequest.TestCase testCase : judgeRequest.getTestCases()) {
            File tempTextFile = submitFileCreator.createTempTextFile(testCase.getInput(), submitDirPath);
            ExecutionResult result = dockerRunner.run(tempSourceFile, tempTextFile, submitResultDir, judgeRequest.getLanguage(),
                    judgeRequest.getLimitTimeInMS(), judgeRequest.getLimitMemoryInKB());
            if (result.getError() != null) {
                submitDirService.deleteDir(submitDir);
                return new JudgeResponse(Result.FAIL, result.getError());
            }
            if (!outputComparator.compare(result.getOutput(), testCase.getOutput())) {
                submitDirService.deleteDir(submitDir);
                return new JudgeResponse(Result.FAIL, "틀렸습니다.");
            }
        }
        submitDirService.deleteDir(submitDir);
        return new JudgeResponse(Result.SUCCESS, "맞았습니다!!");
    }
}
