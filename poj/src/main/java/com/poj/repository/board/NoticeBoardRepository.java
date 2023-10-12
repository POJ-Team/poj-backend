package com.poj.repository.board;

import com.poj.entity.board.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

}
