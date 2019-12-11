package com.kaebit.boardbackend.Service;

import com.kaebit.boardbackend.Exception.UserNotFoundException;
import com.kaebit.boardbackend.domain.User;
import com.kaebit.boardbackend.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(val -> users.add(val));
        return users;
    }

    public User findById(String id) {
        System.out.println(id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    public User findByUser_id(String user_id) {
        User user = userRepository.findByUser_id(user_id);
        return user;
    }

    public void updateUser_idById(String id, String user_id) {
        userRepository.updateUser_idById(id, user_id);
    }

    public void updatePasswordById(String id, String password) {
        userRepository.updatePasswordById(id, password);
    }

    public void updateNameById(String id, String name) {
        userRepository.updateNameById(id, name);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User save(User requestUser) {
        User user = userRepository.save(requestUser);
        return user;
    }
}
