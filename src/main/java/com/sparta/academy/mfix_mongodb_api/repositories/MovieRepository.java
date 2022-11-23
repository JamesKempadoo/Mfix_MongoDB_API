package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository <Movie, String> {

    void deleteById (String ID);
    List<Movie> getAllByTitleContains(String title);
    List<Movie> getAllByGenresContaining(String genre);
    List<Movie> getAllByDirectorsContains(String director);
    List<Movie> getAllByYearBetween(int year, int endYear);
}
