package com.kaebit.boardbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoardCommentNotFoundException extends RuntimeException {

    public BoardCommentNotFoundException() { super("Board Comment Not Found"); }
}
