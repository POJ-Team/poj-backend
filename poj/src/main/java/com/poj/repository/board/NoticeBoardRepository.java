package com.poj.repository.board;

import com.poj.dto.board.noticeboardDTO.NoticeBoardListResponseDTO;
import com.poj.entity.board.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    List<NoticeBoardListResponseDTO> findNoticeBoardByOrderByCreatedAtDesc();
}
