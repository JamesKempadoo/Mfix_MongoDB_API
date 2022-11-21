package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.entity.Theaters;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TheaterRepository extends MongoRepository<Theaters,String>{
}
