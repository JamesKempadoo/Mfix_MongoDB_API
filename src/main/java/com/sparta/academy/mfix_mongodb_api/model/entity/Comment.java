package com.sparta.academy.mfix_mongodb_api.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document( collection = "comments")
public class Comment {

    @Id
    public String id;

    @Field
    public String name;

    @Field(targetType = FieldType.DATE_TIME)
    public String date;

    @Field
    public String email;

    @Field(targetType = FieldType.OBJECT_ID, value = "movie_id")
    public String movieId;

    @Field
    public String text;

    public Comment(String date, String name, String email, String movieId, String text) {
        this.name = name;
        this.email = email;
        this.movieId = movieId;
        this.text = text;
        this.date = date;
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