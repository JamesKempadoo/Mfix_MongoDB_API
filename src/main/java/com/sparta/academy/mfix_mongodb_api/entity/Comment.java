package com.sparta.academy.mfix_mongodb_api.entity;

import com.mongodb.lang.NonNull;
import org.bson.codecs.ObjectIdCodec;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document( collection = "comments")
public class Comment {

    @Id
    public String id;

    @Field
    public String name;

    @Field
    public String date;

    @Field
    public String email;

    @Field
    public String movie_id;

    @Field
    public String text;

    public Comment(String id, @NonNull String name, String date, String email, String movie_id, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.email = email;
        this.movie_id = movie_id;
        this.text = text;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String _id) {
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

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
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
                ", movie_id=" + movie_id +
                ", text_id='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}