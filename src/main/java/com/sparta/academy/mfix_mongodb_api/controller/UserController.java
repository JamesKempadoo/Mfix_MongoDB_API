package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.User;
import com.sparta.academy.mfix_mongodb_api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UsersRepository usersRepository;
    @Autowired
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users/all")
    public List<User> getUsers(){
        return usersRepository.findAll();
    }
//    @GetMapping("")
//    public void
}
