package com.sparta.academy.mfix_mongodb_api.model.entity;

public class Viewer{


    private Integer rating;
    private Integer numReviews;

    public Viewer() {
    }

    public Viewer(Integer rating, Integer numReviews) {
        this.rating = rating;
        this.numReviews = numReviews;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }
}
