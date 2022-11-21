package com.sparta.academy.mfix_mongodb_api.entity;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "movies")
public class Movie{

    @Id
    private String id;

    private int year;

    private List<String> directors;

    private double runtime;

    private List<String> countries;

    private String title;

    private String type;

    @BsonProperty("num_mflix_comments")
    private int numberOfComments;

    private List<String> cast;

    // Embedded Class
    private Tomatoes tomatoes;

    private String fullplot;

    // Embedded Class
    private Imdb imdb;

    private String plot;

    private List<String> genres;

    private String lastupdated;

    // Link to image
    private String poster;

    // DateTime Epoch
    private int released;


    public Movie() {
    }

    public Movie(String id, int year, List<String> directors, double runtime, List<String> countries, String title, String type, int numberOfComments, List<String> cast, Tomatoes tomatoes, String fullplot, Imdb imdb, String plot, List<String> genres, String lastupdated, String poster, int released) {
        this.id = id;
        this.year = year;
        this.directors = directors;
        this.runtime = runtime;
        this.countries = countries;
        this.title = title;
        this.type = type;
        this.numberOfComments = numberOfComments;
        this.cast = cast;
        this.tomatoes = tomatoes;
        this.fullplot = fullplot;
        this.imdb = imdb;
        this.plot = plot;
        this.genres = genres;
        this.lastupdated = lastupdated;
        this.poster = poster;
        this.released = released;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public Tomatoes getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(Tomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    public String getFullplot() {
        return fullplot;
    }

    public void setFullplot(String fullplot) {
        this.fullplot = fullplot;
    }

    public Imdb getImdb() {
        return imdb;
    }

    public void setImdb(Imdb imdb) {
        this.imdb = imdb;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }
}