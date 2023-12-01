package com.poj.repository.board;

import java.util.*;

import com.poj.dto.board.FreeBoardListResponseDTO;
import com.poj.entity.board.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoardListResponseDTO> findFreeBoardByOrderByCreatedAtDesc();
}