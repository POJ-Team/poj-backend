package com.poj.entity.problem;

import com.poj.repository.problem.ProblemRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProblemTest {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    EntityManager em;

    @Test
    void create() {
        Problem problem = Problem.builder()
                .title("test")
                .difficulty(EProblemDifficulty.DIFFICULTY_EASY)
                .availableLanguage(Set.of(EAvailableLanguage.LANGUAGE_C, EAvailableLanguage.LANGUAGE_JAVA))
                .build();
        problemRepository.save(problem);

        // 영속성 컨텍스트 초기화
        em.flush();
        em.clear();

        Problem findProblem = problemRepository.findById(problem.getId()).get();
        assertEquals(problem.getTitle(), findProblem.getTitle());
        assertEquals(problem.getDifficulty(), findProblem.getDifficulty());
        assertEquals(problem.getAvailableLanguage(), findProblem.getAvailableLanguage());
    }

    @Test
    void create_with_ProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.builder()
                .info("test")
                .inputExample("test")
                .outputExample("test")
                .build();
        Problem problem = Problem.builder()
                .title("test")
                .difficulty(EProblemDifficulty.DIFFICULTY_EASY)
                .availableLanguage(Set.of(EAvailableLanguage.LANGUAGE_C, EAvailableLanguage.LANGUAGE_JAVA))
                .problemDetail(problemDetail)
                .build();
        problemRepository.save(problem);

        // 영속성 컨텍스트 초기화
        em.flush();
        em.clear();

        Problem findProblem = problemRepository.findByIdWithProblemDetail(problem.getId()).get();
        assertEquals(problem.getTitle(), findProblem.getTitle());
        assertEquals(problem.getDifficulty(), findProblem.getDifficulty());
        assertEquals(problem.getAvailableLanguage(), findProblem.getAvailableLanguage());
        assertEquals(problem.getProblemDetail().getInfo(), findProblem.getProblemDetail().getInfo());
        assertEquals(problem.getProblemDetail().getInputExample(), findProblem.getProblemDetail().getInputExample());
        assertEquals(problem.getProblemDetail().getOutputExample(), findProblem.getProblemDetail().getOutputExample());

        // fetch lazy 전략으로 인해 프록시 객체만 들고 있는지 확인
        em.flush();
        em.clear();

        Problem findProblem2 = problemRepository.findById(problem.getId()).get();
        assertEquals(problem.getTitle(), findProblem2.getTitle());
        assertEquals(problem.getDifficulty(), findProblem2.getDifficulty());
        assertEquals(problem.getAvailableLanguage(), findProblem2.getAvailableLanguage());

        boolean initialized = Hibernate.isInitialized(findProblem2.getProblemDetail());
        assertFalse(initialized);

        // 실제로 LazyInitializationException 이 발생하는지 확인
        // LazyInitializationException 은 프록시 객체가 영속성 컨텍스트가 종료된 후 참조하려고 할 때 발생합니다.

        // 영속성 컨텍스트 초기화
        em.flush();
        em.clear();

        assertThrows(LazyInitializationException.class, () -> {
            findProblem2.getProblemDetail().getInfo();
        });
    }
}