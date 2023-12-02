package com.poj.repository.board;

import com.poj.dto.board.freeboardDTO.FreeBoardListResponseDTO;
import com.poj.entity.board.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoardListResponseDTO> findFreeBoardByOrderByCreatedAtDesc();
}

