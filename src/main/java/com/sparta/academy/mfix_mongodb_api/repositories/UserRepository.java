package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String>{

    boolean existsUserByEmail(String email);
}
