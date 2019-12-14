package com.kaebit.boardbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already Exist")
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() { super("Exist Data"); }

    public AlreadyExistException(String userId) { super("Exist User id : " + userId); }

}
