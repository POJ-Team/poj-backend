package com.poj.controller.board;

import com.poj.dto.board.noticeboardDTO.*;
import com.poj.entity.board.NoticeBoard;
import com.poj.service.board.NoticeBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeBoardController {
    private final NoticeBoardService noticeBoardService;

    public NoticeBoardController(NoticeBoardService noticeBoardService) {

        this.noticeBoardService = noticeBoardService;
    }

    @PostMapping("/createnotice")
    public ResponseEntity<String> createFreeBoard(@RequestBody NoticeBoardRequestDTO noticeBoardRequestDTO){
        NoticeBoardResponseDTO noticeBoardResponseDTO = noticeBoardService.createBoard(noticeBoardRequestDTO);
        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping("/noticeboards")
    public List<NoticeBoardListResponseDTO> getAllBoards(){
        return noticeBoardService.findAllNoticeBoard();
    }

    @GetMapping("/noticeboards/{id}")
    public NoticeBoardResponseDTO getOneBoard(@PathVariable Long id){
        return noticeBoardService.findOneBoard(id);
    }

    @PutMapping("/noticeboards/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody NoticeBoardRequestDTO noticeBoardRequestDTO){
        noticeBoardService.updateBoard(id, noticeBoardRequestDTO);
        return ResponseEntity.ok(
                "글이 성공적으로 수정되었습니다."
        );
    }

    @DeleteMapping("/noticeboards/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id){
        noticeBoardService.deleteBoard(id);
        return ResponseEntity.ok(
                "글이 성공적으로 삭제되었습니다"
        );
    }
}
