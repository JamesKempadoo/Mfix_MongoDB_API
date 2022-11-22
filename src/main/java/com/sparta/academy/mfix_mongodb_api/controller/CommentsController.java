package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.Comment;
import com.sparta.academy.mfix_mongodb_api.repositories.CommentRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CommentsController {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments/all")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

}
