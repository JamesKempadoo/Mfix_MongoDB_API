package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.model.entity.User;
import com.sparta.academy.mfix_mongodb_api.exceptions.IDNotFoundException;
import com.sparta.academy.mfix_mongodb_api.logging.LoggerSingleton;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {

    private UserRepository userRepository;
    private Logger logger = LoggerSingleton.getSingleton().getLogger();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) throws IDNotFoundException {
        return userRepository.findById(id).orElseThrow(()
                -> new IDNotFoundException(404, id, "ID not found")
        );
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PatchMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
