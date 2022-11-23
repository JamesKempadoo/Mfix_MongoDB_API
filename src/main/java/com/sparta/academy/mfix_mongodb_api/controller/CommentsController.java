package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.Comment;
import com.sparta.academy.mfix_mongodb_api.repositories.CommentRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentsController {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository, UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/comments/all")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/id/{id}")
    public Optional<Comment> getCommentsByText(@PathVariable String id) {
        return commentRepository.findById(id);
    }

    // INSERT COMMENT:
    @PostMapping("/comments")
    public ResponseEntity<Comment> insertComment(@RequestBody Comment comment) {

        HttpStatus status = HttpStatus.OK;
        Comment insertedComment = null;

        if (isCommentBodyValid(comment)) {
            insertedComment = commentRepository.save(comment);
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(insertedComment, status);
    }


    // UPDATE COMMENT [ updates comment text ]
    @PutMapping("/comments/id/{id}")
    public ResponseEntity<Comment> updateCommentWithID(@RequestBody String text, @PathVariable String id) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Comment body = null;

        if ( commentRepository.existsById(id) ) {
            Comment comment = commentRepository.findCommentById(id);
            comment.setText(text);// Update Comment
            body = commentRepository.save(comment);
        }
        return new ResponseEntity<>(body, status);
    }


    @DeleteMapping("/comments/all/email/{email}")
    public ResponseEntity<String> deleteAllCommentsByUserEmail(@PathVariable String email) {

        HttpStatus status = HttpStatus.OK;
        String body = "ALL COMMENTS SUCCESSFULLY DELETED";

        if ( !userRepository.existsUserByEmail(email) ) {
            status = HttpStatus.NOT_FOUND;
            body = "EMAIL DOES NOT EXIST";
        } else {
            commentRepository.deleteAllByEmail(email);
        }
        return new ResponseEntity<>(body, status);
    }


    @DeleteMapping("/comments/id/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id) {

        HttpStatus status = HttpStatus.OK;
        String body = "COMMENT SUCCESSFULLY DELETED";

        if ( !commentRepository.existsById(id) ) {
            status = HttpStatus.NOT_FOUND;
            body = "ID DOES NOT EXIST";
        } else {
            commentRepository.deleteById(id);
        }
        return new ResponseEntity<>(body, status);
    }

    public boolean isCommentBodyValid(Comment comment) {
        return isValueValid(comment.getDate()) &&
                isValueValid(comment.getEmail()) &&
                isValueValid(comment.getName()) &&
                isValueValid(comment.getText()) &&
                isValueValid(comment.getMovie_id());
    }

    public boolean isValueValid(String value) {
        return value != null && value.length() >0;
    }

}
