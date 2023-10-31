package com.poj.repository.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
Problem을 저장하는 JPARepository
 */
@Primary
public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemRepositoryQuerydsl{

    Optional<Problem> findById(Long id); // problem only

    Page<Problem> findAllByTitleContains(String title, PageRequest pageRequest);

    Page<Problem> findAllByAvailableLanguage(EAvailableLanguage availableLanguage, PageRequest pageRequest);

    Page<Problem> findAllByDifficulty(EProblemDifficulty problemDifficulty, PageRequest pageRequest);

}
