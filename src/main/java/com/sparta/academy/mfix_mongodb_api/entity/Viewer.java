package com.sparta.academy.mfix_mongodb_api.entity;

public class Viewer{


    private int rating;
    private int numReviews;

    public Viewer() {
    }

    public Viewer(int rating, int numReviews) {
        this.rating = rating;
        this.numReviews = numReviews;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }
}
