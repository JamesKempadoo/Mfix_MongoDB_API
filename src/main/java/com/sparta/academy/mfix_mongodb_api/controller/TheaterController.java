package com.sparta.academy.mfix_mongodb_api.controller;

//import com.sparta.academy.mfix_mongodb_api.entity.Theater;

import com.sparta.academy.mfix_mongodb_api.entity.theater.Location;
import com.sparta.academy.mfix_mongodb_api.entity.theater.TheaterDTO;
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
    public List<TheaterDTO> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @GetMapping("/theaters/{id}")
    public TheaterDTO getTheaterByID(@PathVariable Integer id) throws NoTheaterFoundException {

        TheaterDTO theater = null;
        theater = theaterRepository.getTheaterDTOByTheaterId(id);

        if(theater == null){
            throw new NoTheaterFoundException();
        }
        return theater;
    }

    @PostMapping("/theaters/new")
    public TheaterDTO addNewTheater(@RequestBody TheaterDTO theater) {
        if(theaterRepository.findAll().contains(theaterRepository.getTheaterDTOByTheaterId(theater.getTheaterId()))){
            throw new RuntimeException();
        }
        theaterRepository.save(theater);
        return theater;
    }

    @PatchMapping(value = "/theaters/{id}", consumes = "application/json")
    public TheaterDTO updateTheaterLocationByTheaterID(@PathVariable Integer id, @RequestBody Location location) throws NoTheaterFoundException {
        TheaterDTO theater = theaterRepository.getTheaterDTOByTheaterId(id);

        System.out.println(location.toString());
        if(theater != null){
            theater.setLocation(location);
            theaterRepository.save(theater);
        } else {
            throw new NoTheaterFoundException();
        }

        return theater;
    }

    @DeleteMapping("/theaters/{id}")
    public String removeTheaterbyId(@PathVariable Integer id){

        if(theaterRepository.existsByTheaterId(id)){
            theaterRepository.deleteTheaterDTOByTheaterId(id);

        } else {
            throw new NoTheaterFoundException();
        }
        return "Theater removed successfully";

    }
}