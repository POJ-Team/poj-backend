package com.poj.controller.board;

import com.poj.dto.board.FreeBoardListResponseDTO;
import com.poj.dto.board.FreeBoardRequestDTO;
import com.poj.dto.board.FreeBoardResponseDTO;
import com.poj.entity.board.FreeBoard;
import com.poj.service.board.FreeBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @PostMapping("/createfree")
    public ResponseEntity<String> createFreeBoard(@RequestBody FreeBoardRequestDTO freeBoardRequestDTO){
        FreeBoardResponseDTO freeBoardResponseDTO = freeBoardService.createBoard(freeBoardRequestDTO);
        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping("/freeboards")
    public List<FreeBoardListResponseDTO> getAllBoards(){
        return freeBoardService.findAllFreeBoard();
    }

    @GetMapping("/freeboards/{id}")
    public FreeBoardResponseDTO getOneBoard(@PathVariable Long id){
        return freeBoardService.findOneBoard(id);
    }

    @PutMapping("/freeboards/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody FreeBoardRequestDTO freeBoardRequestDTO){
        freeBoardService.updateBoard(id, freeBoardRequestDTO);
        return ResponseEntity.ok(
            "글이 성공적으로 수정되었습니다."
        );
    }

    @DeleteMapping("/freeboards/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id){
        freeBoardService.deleteBoard(id);
        return ResponseEntity.ok(
            "글이 성공적으로 삭제되었습니다"
        );
    }
}
