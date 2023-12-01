package com.poj.exception.board;

public class BoardServiceException extends RuntimeException{
    public BoardServiceException(String content){
        super(String.format("%s", content));
    }
}