package com.kaebit.boardbackend.Controller;

import com.kaebit.boardbackend.Exception.ForbiddenException;
import com.kaebit.boardbackend.Exception.TokenErrorException;
import com.kaebit.boardbackend.Exception.WrongDataException;
import com.kaebit.boardbackend.domain.Board;
import com.kaebit.boardbackend.domain.User;
import com.kaebit.boardbackend.Security.JwtTokenUtil;
import com.kaebit.boardbackend.Service.BoardService;
import com.kaebit.boardbackend.Service.UserService;
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
}
