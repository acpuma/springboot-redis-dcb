package com.dailycodebuffer.controller;

import com.dailycodebuffer.model.User;
import com.dailycodebuffer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        boolean result = userService.saveUser(user);
        if (result) {
            return ResponseEntity.ok("User created success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> fetchAllUser() {
        List<User> users;
        users = userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id) {
        User user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        return result ? ResponseEntity.ok("User deleted") : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
