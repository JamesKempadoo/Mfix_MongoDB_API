package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.Theaters;
import com.sparta.academy.mfix_mongodb_api.repositories.TheatersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TheaterController {

    private final TheatersRepository theatersRepository;

    @Autowired
    public TheaterController(TheatersRepository theatersRepository) {
        this.theatersRepository = theatersRepository;
    }


    @GetMapping("/theaters/all")
    public List<Theaters> getUsers() {
        return theatersRepository.findAll();
    }
//    @GetMapping("")
//    public void

}