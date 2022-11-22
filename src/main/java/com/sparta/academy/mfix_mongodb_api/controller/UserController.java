package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.User;
import com.sparta.academy.mfix_mongodb_api.exceptions.IDNotFoundException;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable String id) throws IDNotFoundException {
        return userRepository.findById(id).orElseThrow(()
                -> new IDNotFoundException(404, id, "ID not found")
        );
    }

    
}
