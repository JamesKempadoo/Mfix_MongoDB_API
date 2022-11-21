package com.sparta.academy.mfix_mongodb_api.entity;

public class Imdb{
    private String id;
    private Integer rating;
    private Integer votes;

    public Imdb() {
    }

    public Imdb(String id, Integer rating, Integer votes) {
        this.id = id;
        this.rating = rating;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
