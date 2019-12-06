package com.kaebit.boardbackend.Service;

import com.kaebit.boardbackend.Exception.UserNotFoundException;
import com.kaebit.boardbackend.Model.User;
import com.kaebit.boardbackend.Repository.UserRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(val -> users.add(val));
        return users;
    }

    public User findByPk(String pk) {
        User user = userRepository.findByPk(pk);
        if(user == null) {
            throw new UserNotFoundException("User", "pk", pk);
        }
        return user;
    }

    public User updateByPk(String pk, User user) {
        User updatedUser = userRepository.updateByPk(pk, user);
        if(updatedUser == null) {
            throw new UserNotFoundException("User", "pk", pk);
        }
        return updatedUser;
    }

    public void deleteByPk(String pk) {
        User user = userRepository.findByPk(pk);
        if(user == null) {
            throw new UserNotFoundException("User", "pk", pk);
        }

        userRepository.deleteByPk(user.getPk());
    }
}
