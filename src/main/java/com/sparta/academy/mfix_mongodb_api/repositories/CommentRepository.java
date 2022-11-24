package com.sparta.academy.mfix_mongodb_api.repositories;

import com.sparta.academy.mfix_mongodb_api.model.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface CommentRepository extends MongoRepository <Comment, String> {
    List<Comment> findCommentByMovieId(String movieId);
    void deleteAllByEmail(String email);
    Comment findCommentById(String id);

    List<Comment> findCommentByTextContaining(String text);

    List<Comment> findCommentByNameContaining(String name);

    List<Comment> findCommentByDateBetween(LocalDateTime beforeTime, LocalDateTime afterTime);

    List<Comment> findCommentByEmail(String email);


}
