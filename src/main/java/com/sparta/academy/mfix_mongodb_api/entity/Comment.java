package com.sparta.academy.mfix_mongodb_api.entity;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( collection = "comments")
public class Comment {

    @Id
    @Field
    public String _id;

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


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Object getMovie_id() {
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
                "_id=" + _id +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", movie_id=" + movie_id +
                ", text_id='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}