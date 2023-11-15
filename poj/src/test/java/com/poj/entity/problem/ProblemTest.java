package com.poj.entity.problem;

import com.poj.dto.problem.ProblemCreateAndUpdateRequest;
import com.poj.dto.problem.ProblemReadRequest;
import com.poj.dto.problem.ProblemResponse;
import com.poj.service.problem.ProblemCreateService;
import com.poj.service.problem.ProblemReadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@SpringBootTest
public class ProblemTest {

    @Autowired
    private ProblemReadService problemReadService;

    @Autowired
    private ProblemCreateService problemCreateService;

    // @Test에 Transactional이 걸려 있으면 DB에 반영되지 않고 롤백됩니다.
    // DB에 들어가는것을 확인하려면 @Transactional을 지우고 실행해주세요.
    @Transactional
    @Test
    void createAndFind(){
        // 생성할 문제 개수
        int NUM = 500;

        // 문제를 500개 생성
        createProblem(NUM);

        // Read Request 생성. 0번째 페이지. 표시되는 개수는 20개
        int PAGENUM = 0;
        int SIZE = 10;
        ProblemReadRequest problemReadRequest = new ProblemReadRequest();
        problemReadRequest.setPageNumber(PAGENUM);
        problemReadRequest.setSize(SIZE);

        // request 보냄
//        Page<ProblemResponse> pp = problemReadService.getProblemsByTitle("25", problemReadRequest);// title에 25가 포함된 문제
        Page<ProblemResponse> pp = problemReadService.getProblemsByDifficulty(EProblemDifficulty.DIFFICULTY_NORMAL, problemReadRequest);// 난이도가 Normal인 문제


        // 잘 받았는지 출력해봄
        System.out.println(pp.stream().count());
        pp.stream().forEach(problemResponse -> System.out.println(
                String.format(
                        problemResponse.getTitle() +
                                "의 난이도는 " +
                                problemResponse.getDifficulty() +
                                "입니다."
                )));


    }


    private void createProblem(int NUM){
        // 문제마다 property 지정해주고 생성

        Random random = new Random();
        EProblemDifficulty[] enums_diff = EProblemDifficulty.values();

        for(int i=0; i<NUM; i++){
            ProblemCreateAndUpdateRequest problemCreateAndUpdateRequest = new ProblemCreateAndUpdateRequest();
            problemCreateAndUpdateRequest.setTitle(i + "번째 문제");
            problemCreateAndUpdateRequest.setDifficulty(enums_diff[random.nextInt(enums_diff.length)]);
            Set<EAvailableLanguage> setset = new HashSet<>();
            if(i%2 == 0) setset.add(EAvailableLanguage.LANGUAGE_JAVA);
            if(i%2 != 0) setset.add(EAvailableLanguage.LANGUAGE_CPP);
            if(i%7 == 0) setset.add(EAvailableLanguage.LANGUAGE_C);
            if(i%23 == 0) setset.add(EAvailableLanguage.LANGUAGE_PYTHON);
            problemCreateAndUpdateRequest.setAvailableLanguage(setset);

            problemCreateAndUpdateRequest.setInfo(i + "번째 문제의 설명입니다");
            problemCreateAndUpdateRequest.setInputExample("");
            problemCreateAndUpdateRequest.setOutputExample("");
            problemCreateAndUpdateRequest.setTimeLimit(0L);
            problemCreateAndUpdateRequest.setMemoryLimit(0L);

            problemCreateService.CreateProblem(problemCreateAndUpdateRequest);
        }

    }
}
