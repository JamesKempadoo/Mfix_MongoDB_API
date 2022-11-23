package com.sparta.academy.mfix_mongodb_api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Document( collection = "comments")
public class Comment {

    private static DateTimeFormatter format
            = DateTimeFormatter.ofPattern("EEE-LLL-d-HH:mm:ss-zzz-yyyy", Locale.ENGLISH);

    @Id
    public String id;

    @Field
    public String name;

    @Field
    public String date;

    @Field
    public String email;

    @Field(targetType = FieldType.OBJECT_ID, value = "movie_id")
    public String movieId;

    @Field
    public String text;

    public Comment(String name, String email, String movieId, String text) {
        this.name = name;
        this.date = LocalDateTime.now().format(format);
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

    public LocalDateTime getDateAsLocalDateTime() {
        return LocalDateTime.parse(date.replace(" ", "-"), format);
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