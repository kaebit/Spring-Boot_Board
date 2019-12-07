package com.kaebit.boardbackend;

import com.kaebit.boardbackend.Model.Board;
import com.kaebit.boardbackend.Model.User;
import com.kaebit.boardbackend.Repository.BoardRepository;
import com.kaebit.boardbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardBackendApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;


    public static void main(String[] args) {
        SpringApplication.run(BoardBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("김경백", "test1", "test1234"));
        userRepository.save(new User("김대희", "test2", "test1234"));
        User user = userRepository.save(new User("김학동", "test3", "test1234"));
        boardRepository.save(new Board(user, "테스트", "테스트용", user.getName()));
    }
}
