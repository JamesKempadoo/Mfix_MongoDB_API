package com.sparta.academy.mfix_mongodb_api.model.entity.theater;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "theaters")
public class Theater {


    @Field
    private Integer theaterId;

    @Field
    private Location location;

    @Id
    @Field
    private String id;


    public Integer getTheaterId() {
        return theaterId;
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "TheaterDTO{" +
                        "theaterId = '" + theaterId + '\'' +
                        ",location = '" + location + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }
}