package com.kaebit.boardbackend.service;

import com.kaebit.boardbackend.domain.BoardCommentReply;
import com.kaebit.boardbackend.domain.BoardCommentReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardCommentReplyService {

    @Autowired
    BoardCommentReplyRepository boardCommentReplyRepository;

    public BoardCommentReply save(BoardCommentReply requestedBoardCommentReply) {
        BoardCommentReply boardCommentReply = boardCommentReplyRepository.save(requestedBoardCommentReply);
        return boardCommentReply;
    }
}
