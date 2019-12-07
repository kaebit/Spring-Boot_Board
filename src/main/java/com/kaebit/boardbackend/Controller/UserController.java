package com.kaebit.boardbackend.Controller;

import com.kaebit.boardbackend.Exception.*;
import com.kaebit.boardbackend.Model.User;
import com.kaebit.boardbackend.Security.JwtTokenUtil;
import com.kaebit.boardbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/one")
    public ResponseEntity<User> getUser(@RequestHeader("id") String id) {
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody User requestUser) {
        User user = userService.findByUser_id(requestUser.getUser_id());
        if(user != null) {
            throw new AlreadyExistException(user.getUser_id());
        }
        userService.save(requestUser);
        return "{" + "success: " + "true" + "}";
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String login(@RequestParam("user_id") String user_id, @RequestParam("password") String password) {
        User user = userService.findByUser_id(user_id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        if(!user.getPassword().equals(password)) {
            throw new WrongDataException();
        }

        String token = jwtTokenUtil.issueToken(user.getUser_id(), user.getId());
        return "{" + "success: " + "true, " + "token: " + token + "}";
    }

    @PutMapping(value = "/change/password",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String changePassword(@RequestHeader("accessToken") String accessToken, @RequestParam("password") String password) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        userService.updatePasswordById(id, password);

        return "{" + "success: " + "true, "+ "}";
    }

    @PutMapping(value = "/change/userid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String changeUserID(@RequestHeader("accessToken") String accessToken, @RequestParam("user_id") String user_id) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        userService.updateUser_idById(id, user_id);
        return "{" + "success: " + "true" + "}";
    }

    @PutMapping(value = "/change/username", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String changeUserName(@RequestHeader("accessToken") String accessToken, @RequestParam("name") String name) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        userService.updateNameById(id, name);
        return "{" + "success: " + "true, "+ "}";
    }

    @DeleteMapping
    public String deleteUser(@RequestHeader("accessToken") String accessToken) {
        String id = jwtTokenUtil.getUserIdFromToken(accessToken);
        if(id == null) {
            throw new TokenErrorException();
        }
        User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        userService.deleteById(id);
        return "{" + "success: " + "true" + "}";
    }

    @RequestMapping("/*")
    public void notFound() {
        throw new NotFoundException();
    }
}
