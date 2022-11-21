package com.sparta.academy.mfix_mongodb_api.entity;

import com.mongodb.lang.Nullable;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "movies")
public class Movie{

    @Id
    private String id;

    @Field
    private String year;

    @Field
    private List<String> directors;

    @Field
    private Integer runtime;

    @Field
    private List<String> countries;

    @Field
    private String title;

    @Field
    private String type;

    @Field
    //@BsonProperty("num_mflix_comments")
    @Nullable
    private Integer num_mflix_comments;

    @Field
    private List<String> cast;

    @Field
    // Embedded Class
    private Tomatoes tomatoes;

    @Field
    private String fullplot;

    // Embedded Class
    @Field
    private Imdb imdb;

    @Field
    private String plot;

    @Field
    private List<String> genres;

    @Field
    private String lastupdated;

    @Field
    // Link to image
    private String poster;

    // DateTime Epoch
    @Field
    private String released;

    public Movie() {
    }

    public Movie(String id, String year, List<String> directors, Integer runtime, List<String> countries, String title, String type, @Nullable Integer num_mflix_comments, List<String> cast, Tomatoes tomatoes, String fullplot, Imdb imdb, String plot, List<String> genres, String lastupdated, String poster, String released) {
        this.id = id;
        this.year = year;
        this.directors = directors;
        this.runtime = runtime;
        this.countries = countries;
        this.title = title;
        this.type = type;
        this.num_mflix_comments = num_mflix_comments;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
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

    @Nullable
    public Integer getNum_mflix_comments() {
        return num_mflix_comments;
    }

    public void setNum_mflix_comments(@Nullable Integer num_mflix_comments) {
        this.num_mflix_comments = num_mflix_comments;
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

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
}