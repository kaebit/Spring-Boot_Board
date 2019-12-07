package com.kaebit.boardbackend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenErrorException extends RuntimeException {

    public TokenErrorException() { super("Token error"); }
}
