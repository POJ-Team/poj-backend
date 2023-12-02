package com.poj.service.board;

import java.util.*;
import com.poj.dto.board.freeboardDTO.FreeBoardListResponseDTO;
import com.poj.dto.board.freeboardDTO.FreeBoardRequestDTO;
import com.poj.dto.board.freeboardDTO.FreeBoardResponseDTO;
import com.poj.entity.board.FreeBoard;
import com.poj.exception.board.BoardServiceException;
import com.poj.repository.board.FreeBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;

    public FreeBoardResponseDTO createBoard(FreeBoardRequestDTO freeBoardRequestDTO){
        FreeBoard freeBoard = new FreeBoard(freeBoardRequestDTO);
        freeBoardRepository.save(freeBoard);
        return new FreeBoardResponseDTO(freeBoard);
    }

    public List<FreeBoardListResponseDTO> findAllFreeBoard() {
        try{
            List<FreeBoard> boardList = freeBoardRepository.findAll();
            List<FreeBoardListResponseDTO> responseDTOList = new ArrayList<>();
            for (FreeBoard freeBoard: boardList){
                responseDTOList.add(
                        new FreeBoardListResponseDTO(freeBoard)
                );
            }
            return responseDTOList;
        } catch (Exception e){
            throw new BoardServiceException("게시물이 존재하지 않습니다.");
        }
    }

    public FreeBoardResponseDTO findOneBoard(Long id){
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new FreeBoardResponseDTO(freeBoard);
    }

    public Long updateBoard(Long id, FreeBoardRequestDTO freeBoardRequestDTO){
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 ID가 존재하지 않습니다.")
        );
        freeBoard.update(freeBoardRequestDTO);
        return freeBoard.getId();
    }
    public Long deleteBoard(Long id){
        freeBoardRepository.deleteById(id);
        return id;
    }
}