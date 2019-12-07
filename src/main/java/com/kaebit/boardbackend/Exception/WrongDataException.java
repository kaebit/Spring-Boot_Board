package com.kaebit.boardbackend.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongDataException extends RuntimeException {

    public WrongDataException() { super("Wrong Data"); }
}
