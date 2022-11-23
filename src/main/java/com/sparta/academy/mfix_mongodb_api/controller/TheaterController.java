package com.sparta.academy.mfix_mongodb_api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sparta.academy.mfix_mongodb_api.entity.Theater;
import com.sparta.academy.mfix_mongodb_api.exceptions.NoTheaterFoundException;
import com.sparta.academy.mfix_mongodb_api.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }


    @GetMapping("/theaters/all")
    public List<Theater> getUsers() {
        System.out.println("hi");return theaterRepository.findAll();
    }

    @GetMapping("/theaters/{id}")
    public Theater getTheaterLocationByID(@PathVariable Integer id) throws NoTheaterFoundException {

        Theater theater = null;
        try {
            theater = theaterRepository.getTheatersByTheaterId(id);
        } catch (NoTheaterFoundException e) {
            e.getMessage();
        }
        if(theater == null){
            throw new NoTheaterFoundException();
        }
        return theater;
    }

    @PostMapping("/theaters/new")
    public Theater addNewTheater(@RequestBody Theater theater) {
        if(theaterRepository.findAll().contains(theaterRepository.getTheatersByTheaterId(theater.getTheaterId()))){
            throw new RuntimeException();
        }
        theaterRepository.save(theater);
        return theater;
    }

    @PatchMapping("/theaters/patch/{id}/")
    public Theater updateTheaterLocationByTheaterID(@PathVariable Integer id) throws NoTheaterFoundException {

        Theater theater = null;
        try {
            theater = theaterRepository.getTheatersByTheaterId(id);
        } catch (NoTheaterFoundException e) {
            e.getMessage();
        }


        if(theater == null){
            throw new NoTheaterFoundException();
        }
        return theater;
    }
}