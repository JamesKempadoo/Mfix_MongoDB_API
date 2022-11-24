package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.model.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository <Comment, String> {
    List<Comment> findCommentByMovieId(String movieId);
    void deleteAllByEmail(String email);
    Comment findCommentById(String id);
}
