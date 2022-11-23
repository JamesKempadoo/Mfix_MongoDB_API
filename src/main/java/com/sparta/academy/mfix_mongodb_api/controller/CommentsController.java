package com.sparta.academy.mfix_mongodb_api.controller;

import com.sparta.academy.mfix_mongodb_api.entity.Comment;
import com.sparta.academy.mfix_mongodb_api.repositories.CommentRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CommentsController {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    //GET ---------------------------------------------------------------
    @GetMapping("/comments/all")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/id/{id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable String id) {

        HttpStatus status = HttpStatus.OK;
        Comment comment = null;
        if ( commentRepository.existsById(id) ) {
            comment = commentRepository.findCommentById(id);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(comment, status);
    }

    @GetMapping("/comments/name/{name}")
    public List<Comment> getCommentsByName(@PathVariable String name) {
        List<Comment> listOfEmails = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()){
            if (comment.name.contains(name)){
                listOfEmails.add(comment);
            }
        }
        return listOfEmails;
    }

    @GetMapping("/comments/date/{date}")
    public ResponseEntity<List<Comment>> getCommentsByDateYear( @PathVariable String date ){
        HttpStatus status = HttpStatus.OK;
        String body = "Bad request: Not a date";
        List<Comment> listOfComments = new ArrayList<>();
        if (date==null){
            return new ResponseEntity<>(listOfComments, HttpStatus.BAD_REQUEST);
        }
        List<String> YMD = List.of(date.split("-"));
        List<Integer> YMDN = new ArrayList<>();
        for (String s: YMD){
            if (s.matches("\\d+(\\.\\d+)?")){
                YMDN.add(Integer.parseInt(s));
            }else {
                return new ResponseEntity<>(listOfComments, HttpStatus.BAD_REQUEST);
            }
        }
        for (Comment comment : commentRepository.findAll()){
            if (YMDN.size()!=0
                    && comment.getDate().getYear() == YMDN.get(0)){
                if (YMDN.size()==1){
                    listOfComments.add(comment);
                } else if (comment.getDate().getMonthValue() == YMDN.get(1)){
                    if (YMDN.size()==2
                        ||comment.getDate().getDayOfMonth() == YMDN.get(2)){
                        listOfComments.add(comment);
                    }
                }
            }
        }
        return new ResponseEntity<>(listOfComments, status);
    }

    @GetMapping("/comments/movie/{id}")
    public ResponseEntity<List<Comment>> getCommentsByMovie(@PathVariable String id) {
        ResponseEntity<List<Comment>> response;
        try{
            response = new ResponseEntity<>(commentRepository.findCommentByMovieId(id),HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/comments/email/{email}")
    public List<Comment> getEmailsByEmail(@PathVariable String email) {
        List<Comment> listOfEmails = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()){
            if (comment.email.equals(email)){
                listOfEmails.add(comment);
            }
        }
        return listOfEmails;
    }

    @GetMapping("/comments/text/{text}")
    public List<Comment> getCommentsByText(@PathVariable String text) {
        List<Comment> listOfEmails = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()){
            if (comment.text.contains(text.toLowerCase())){
                listOfEmails.add(comment);
            }
        }
        return listOfEmails;
    }

    //Request body with just the words you wanted to input e.g. Minima odit
    @GetMapping("/comments/text/")
    public List<Comment> getCommentsByTextBody(@RequestBody String text) {
        List<Comment> listOfEmails = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()){
            if (comment.text.contains(text)){
                listOfEmails.add(comment);
            }
        }
        return listOfEmails;
    }

    //PUT
    //UPDATE COMMENT [ updates comment text ]
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

    //POST
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

    //DELETES ---------------------------------------------------------------

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
        return  isValueValid(comment.getEmail()) &&
                isValueValid(comment.getName()) &&
                isValueValid(comment.getText()) &&
                isValueValid(comment.getMovie_id());
    }

    public boolean isValueValid(String value) {
        return value != null && value.length() >0;
    }

}
