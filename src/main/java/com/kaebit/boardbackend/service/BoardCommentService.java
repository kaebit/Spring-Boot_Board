package com.kaebit.boardbackend.service;

import com.kaebit.boardbackend.domain.BoardComment;
import com.kaebit.boardbackend.domain.BoardCommentRepository;
import com.kaebit.boardbackend.exception.BoardCommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardCommentService {

    @Autowired
    BoardCommentRepository boardCommentRepository;

    public BoardComment findById(Integer id) {
        BoardComment boardComment = boardCommentRepository.findById(id).orElseThrow(() -> new BoardCommentNotFoundException());
        return boardComment;
    }

    public BoardComment save(BoardComment requestBoardComment) {
        BoardComment boardComment = boardCommentRepository.save(requestBoardComment);
        return boardComment;
    }

    public void updateById(Integer id, String content) {
        boardCommentRepository.updateById(id, content);
    }

    public void deleteById(Integer id) {
        boardCommentRepository.deleteById(id);
    }
}
