package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.entity.Theater;
import com.sparta.academy.mfix_mongodb_api.exceptions.NoTheaterFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TheaterRepository extends MongoRepository<Theater,String>{

    Theater getTheatersByTheaterId(int id) throws NoTheaterFoundException;
}
