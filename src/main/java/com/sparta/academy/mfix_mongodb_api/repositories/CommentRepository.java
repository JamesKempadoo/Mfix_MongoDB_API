package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository <Comment, String> {


}
