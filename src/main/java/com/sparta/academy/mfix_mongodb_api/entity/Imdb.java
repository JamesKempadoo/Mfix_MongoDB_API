package com.sparta.academy.mfix_mongodb_api.entity;

public class Imdb{
    private String id;
    private int rating;
    private int votes;

    public Imdb() {
    }

    public Imdb(String id, int rating, int votes) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
