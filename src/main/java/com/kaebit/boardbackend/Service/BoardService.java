package com.kaebit.boardbackend.Service;

import com.kaebit.boardbackend.Exception.BoardNotFoundException;
import com.kaebit.boardbackend.domain.Board;
import com.kaebit.boardbackend.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<Board> findAll() {
        List<Board> boards = new ArrayList<>();
        boardRepository.findAll().forEach(val -> boards.add(val));
        return boards;
    }

    public Board findById(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        return board;
    }

    public List<Board> findByUserId(String user_id) {
        List<Board> boards = boardRepository.findByUserId(user_id);
        return boards;
    }

    public void updateById(Integer id, String title, String content) {
        boardRepository.updateById(id, title, content);
    }

    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }

    public Board save(Board requestBoard) {
        Board board = boardRepository.save(requestBoard);
        return board;
    }
}
