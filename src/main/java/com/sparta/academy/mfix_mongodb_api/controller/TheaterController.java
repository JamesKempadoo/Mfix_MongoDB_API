package com.sparta.academy.mfix_mongodb_api.controller;

//import com.sparta.academy.mfix_mongodb_api.entity.Theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.entity.theater.Location;
import com.sparta.academy.mfix_mongodb_api.exceptions.NoTheaterFoundException;
import com.sparta.academy.mfix_mongodb_api.entity.theater.Theater;
import com.sparta.academy.mfix_mongodb_api.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @GetMapping("/theaters/{id}")
    public Theater getTheaterByID(@PathVariable Integer id) throws NoTheaterFoundException {

        Theater theater = null;
        theater = theaterRepository.getTheaterDTOByTheaterId(id);

        if(theater == null){
            throw new NoTheaterFoundException();
        }
        return theater;
    }

    @PostMapping("/theaters/new")
    public ResponseEntity<String> addNewTheater(@RequestBody Theater theater) throws JsonProcessingException {
        ResponseEntity<String> response;
        if(theaterRepository.findAll().contains(theaterRepository.getTheaterDTOByTheaterId(theater.getTheaterId()))){
            System.out.println("non unique id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Theater with id " + theater.getTheaterId() + " already exists");
        } else if(theater.getTheaterId() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TheaterId must be equal or greater than 0");
        } else if(theater.getLocation() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location is null");
        } else if((!(theater.getLocation().getGeo().getCoordinates().get(0) >= -180 && theater.getLocation().getGeo().getCoordinates().get(0) <=180) && !(theater.getLocation().getGeo().getCoordinates().get(1) >= -90 && theater.getLocation().getGeo().getCoordinates().get(1) <=90))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong coordinates introduced.");
        } else if(theater.getLocation().getGeo().getType() == null || theater.getLocation().getGeo().getType().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong geo type introduced.");
        } else if(!theater.getLocation().getAddress().getZipcode().matches("[0-9]{5}")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong zipcode introduced.");
        } else if(theater.getLocation().getAddress().getCity() == null || theater.getLocation().getAddress().getCity().isBlank() || !Character.isUpperCase(theater.getLocation().getAddress().getCity().charAt(0))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong city introduced.");
        } else if(theater.getLocation().getAddress().getStreet1() == null || theater.getLocation().getAddress().getStreet1().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Street field cannot be empty.");
        } else if(theater.getLocation().getAddress().getState() == null || theater.getLocation().getAddress().getState().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("State field cannot be empty.");
        } else if(theater.getId() == null || theater.getId().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID field cannot be empty.");
        } else {
            theaterRepository.insert(theater);
            ObjectMapper objectMapper = new ObjectMapper();
            response = new ResponseEntity<>(objectMapper.writeValueAsString(theater), HttpStatus.OK);
        }
        theaterRepository.save(theater);

        return response;
    }

    @PatchMapping(value = "/theaters/{id}", consumes = "application/json")
    public Theater updateTheaterLocationByTheaterID(@PathVariable Integer id, @RequestBody Location location) throws NoTheaterFoundException {
        Theater theater = theaterRepository.getTheaterDTOByTheaterId(id);

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
    public ResponseEntity<String> removeTheaterbyId(@PathVariable Integer id){

        if(theaterRepository.existsByTheaterId(id)){
            theaterRepository.deleteTheaterDTOByTheaterId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Theater with ID " + id + " has been deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No theater with ID " + id);

    }

}