package com.sparta.academy.mfix_mongodb_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.entity.Movie;
import com.sparta.academy.mfix_mongodb_api.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MovieController {

    private final MovieRepository repository;

    @Autowired
    public MovieController(MovieRepository repository) {

        this.repository = repository;
    }

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String actor,
                                    @RequestParam(required = false) String genre, @RequestParam(required = false) String director,
                                    @RequestParam(required = false) Integer startYear, @RequestParam(required = false) Integer endYear) {
        if(title != null){
            return getAllTitleContains(title);
        } else if (actor != null) {
            return getAllMoviesByActorName(actor);
        } else if(genre != null){
            return getAllByGenre(genre);
        } else if (director != null) {
            return getAllByDirector(director);
        } else if (startYear != null && endYear != null) {
            return getAllBetween(startYear, endYear);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(repository.findAll()),HttpStatus.OK);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing JSON");
            }
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Movie movie = repository.findById(id).get();
            response = new ResponseEntity<>(objectMapper.writeValueAsString(movie), HttpStatus.OK);
        } catch (JsonProcessingException | NoSuchElementException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Movie with ID " + id);
        }
        return response;
    }

    public ResponseEntity<String> getAllTitleContains(String title) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> list = repository.getAllByTitleContains(title);

        if(list.size() == 0) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Movie with " + title + " in the title");
        } else {
            try {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    public ResponseEntity<String> getAllMoviesByActorName(String actorName) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();

        List<Movie> movieList = repository.findAll();
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getCast() != null) {
                for (String actor : movie.getCast()) {
                    if (actor.contains(actorName)) {
                        matchingMovies.add(movie);
                    }
                }
            }
        }
        try {
            if (matchingMovies.isEmpty()) {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No movies found with actor: "+actorName);
            } else {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(matchingMovies), HttpStatus.OK);
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing JSON");
        }
        return response;
    }

    public ResponseEntity<String> getAllByGenre(String genre) {

        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> list = repository.getAllByGenresContaining(genre);

        if (list.size() == 0) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Movie of the Genre: " + genre);
        } else {
            try {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    public ResponseEntity<String> getAllByDirector(String director) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> movieList = repository.findAll();
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getDirectors() != null) {
                for (String directorItem : movie.getDirectors()) {
                    if (directorItem.contains(director)) {
                        matchingMovies.add(movie);
                    }
                }
            }
        }
        try {
            if (matchingMovies.isEmpty()) {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No movies found directed by: "+director);
            } else {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(matchingMovies), HttpStatus.OK);
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing JSON");
        }
        return response;
    }

    public ResponseEntity<String> getAllBetween(int startYear, int endYear) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> list = repository.getAllByYearBetween(startYear, endYear);

        if (list.size() == 0) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Movie released between " + startYear + " and " + endYear);
        } else {
            try {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable String id) {

        try {
            repository.deleteById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Movie with ID " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Movie with ID " + id + " has been deleted Successfully!");
    }

    @PutMapping("/movies/{id}")
    public Movie editMovie(@RequestBody Movie newMovie, @PathVariable String id) {
        return repository.findById(id).map(movie -> {
            movie.setDirectors(newMovie.getDirectors());
            movie.setFullplot(newMovie.getFullplot());
            movie.setNum_mflix_comments(newMovie.getNum_mflix_comments());
            movie.setPlot(newMovie.getPlot());
            movie.setTitle(newMovie.getTitle());
            movie.setGenres(newMovie.getGenres());
            movie.setLastupdated(newMovie.getLastupdated());
            movie.setPoster(newMovie.getPoster());
            movie.setTomatoes(newMovie.getTomatoes());
            movie.setYear(newMovie.getYear());
            movie.setImdb(newMovie.getImdb());
            movie.setReleased(newMovie.getReleased());
            movie.setCast(newMovie.getCast());
            movie.setRuntime(newMovie.getRuntime());
            movie.setCountries(newMovie.getCountries());
            movie.setType(newMovie.getType());
            return repository.save(movie);
        }).orElseGet(() -> {
            newMovie.setId(id);
            return repository.save(newMovie);
        });
    }

    @PostMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMovieByRequestBody(@RequestBody Movie newMovie) {
        ResponseEntity<String> response;
        try {
            //i considered doing this in one if statement but this allows for more specific error msg
            //checking if the important fields are left empty or not, others are optional
            // optional fields can be changed through patch/put anyways
            if (newMovie.getTitle() == null || newMovie.getTitle().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title not allowed to be empty/null!");
            } else if (newMovie.getYear() == null || newMovie.getYear().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Year not allowed to be empty/null!");
            } else if (newMovie.getDirectors() == null || newMovie.getDirectors().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Directors not allowed to be empty/null!");
            } else if (newMovie.getGenres() == null || newMovie.getGenres().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Genres not allowed to be empty/null!");
            } else if (newMovie.getPlot() == null || newMovie.getPlot().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plot not allowed to be empty/null!");
            } else if (newMovie.getReleased() == null || newMovie.getReleased().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Released not allowed to be empty/null!");
            } else {
                newMovie.setId(null);
                newMovie.setNum_mflix_comments(0);
                repository.insert(newMovie);
                ObjectMapper objectMapper = new ObjectMapper();
                response = new ResponseEntity<>(objectMapper.writeValueAsString(newMovie), HttpStatus.OK);
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing JSON");
        }
        //if no issues occured then return the completed
        return response;
    }

    @GetMapping("movies/number")
    public int getNumber() {

        return repository.countMovieByIdNotNull();
    }
}


