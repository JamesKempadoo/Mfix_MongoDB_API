package com.sparta.academy.mfix_mongodb_api.repositories;

//import com.sparta.academy.mfix_mongodb_api.entity.Theater;
import com.sparta.academy.mfix_mongodb_api.model.entity.theater.TheaterDTO;
import com.sparta.academy.mfix_mongodb_api.exceptions.NoTheaterFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface TheaterRepository extends MongoRepository<TheaterDTO,String>{

    TheaterDTO getTheaterDTOByTheaterId(Integer id) throws NoTheaterFoundException;
    boolean existsByTheaterId(Integer id);
    void deleteTheaterDTOByTheaterId(Integer id);
    void removeTheaterDTOByTheaterId(Integer id);

}
