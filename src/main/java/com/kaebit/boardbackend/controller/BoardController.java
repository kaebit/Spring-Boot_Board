package com.kaebit.boardbackend.controller;

import com.kaebit.boardbackend.exception.ForbiddenException;
import com.kaebit.boardbackend.exception.TokenErrorException;
import com.kaebit.boardbackend.exception.WrongDataException;
import com.kaebit.boardbackend.domain.Board;
import com.kaebit.boardbackend.domain.BoardComment;
import com.kaebit.boardbackend.domain.User;
import com.kaebit.boardbackend.security.JwtTokenUtil;
import com.kaebit.boardbackend.service.BoardCommentService;
import com.kaebit.boardbackend.service.BoardService;
import com.kaebit.boardbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    BoardCommentService boardCommentService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<Board> getBoards() {
        List<Board> boards = boardService.findAll();
        return boards;
    }

    @GetMapping(value = "/{id}")
    public Board getBoard(@PathVariable("id") Integer id) {
        Board board = boardService.findById(id);
        return board;
    }

    @GetMapping(value = "/own")
    public List<Board> getOwnBoards(@RequestHeader("accessToken") String accessToken) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        List<Board> boards = boardService.findByUserId(id);
        return boards;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Board createBoard(@RequestHeader("accessToken") String accessToken, @RequestParam("title") String title, @RequestParam("content") String content) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        User user = userService.findById(id);
        System.out.println(content);
        if(title == null || content == null) {
            throw new WrongDataException();
        }
        Board newBoard = new Board(user.getId(), title, content, user.getName());
        Board board = boardService.save(newBoard);
        return board;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String putBoard(@RequestHeader("accessToken") String accessToken, @RequestHeader("board_id") Integer board_id, @RequestParam("title") String title, @RequestParam("content") String content) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        Board board = boardService.findById(board_id);
        User user = userService.findById(id);
        if(!user.getId().equals(board.getUser_id())) {
            throw new ForbiddenException();
        }
        boardService.updateById(board_id, title, content);
        return "SUCCESS";
    }

    @DeleteMapping
    public String deleteBoard(@RequestHeader("accessToken") String accessToken, @RequestHeader("board_id") Integer board_id) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        Board board = boardService.findById(board_id);
        User user = userService.findById(id);
        if(!user.getId().equals(board.getUser_id())) {
            throw new ForbiddenException();
        }
        boardService.deleteById(board_id);
        return "SUCCESS";
    }

    // Board Comment Controllers

    @PostMapping(value = "/comment")
    public BoardComment createBoardComment(@RequestHeader("accessToken") String accessToken, @RequestParam("board_id") Integer board_id, @RequestParam("content") String content) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if (id == null) {
            throw new TokenErrorException();
        }
        Board board = boardService.findById(board_id);
        User user = userService.findById(id);

        BoardComment boardComment = new BoardComment(user.getId(), board.getId(), user.getName(), content);
        BoardComment result = boardCommentService.save(boardComment);

        return result;
    }

    @PutMapping(value = "/comment")
    public String updateBoardComment(@RequestHeader("accessToken") String accessToken, @RequestParam("board_id") Integer board_id, @RequestParam("comment_id") Integer comment_id, @RequestParam("content") String content) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if (id == null) {
            throw new TokenErrorException();
        }
        Board board = boardService.findById(board_id);
        User user = userService.findById(id);
        BoardComment boardComment = boardCommentService.findById(comment_id);
        boardCommentService.updateById(comment_id, content);

        return "SUCCESS";
    }

    @DeleteMapping(value = "/comment")
    public String deleteBoardComment(@RequestHeader("accessToken") String accessToken, @RequestParam("board_id") Integer board_id, @RequestParam("comment_id") Integer comment_id) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if (id == null) {
            throw new TokenErrorException();
        }
        Board board = boardService.findById(board_id);
        BoardComment boardComment = boardCommentService.findById(comment_id);
        if(!(id.equals(boardComment.getUser_id()))) {
            throw new ForbiddenException();
        }
        boardCommentService.deleteById(boardComment.getId());
        return "SUCCESS";
    }
}
