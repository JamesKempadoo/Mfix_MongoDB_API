package com.sparta.academy.mfix_mongodb_api.framework.dto.comments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDTO {

    @JsonProperty
    public String id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String date;

    @JsonProperty
    public String email;

    @JsonProperty
    public String movieId;

    @JsonProperty
    public String text;

    public CommentDTO() {
    }

    public CommentDTO(String id, String name, String date, String email, String movieId, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.email = email;
        this.movieId = movieId;
        this.text = text;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", movieId=" + movieId +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
