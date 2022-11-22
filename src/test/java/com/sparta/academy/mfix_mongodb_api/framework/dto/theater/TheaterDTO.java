package com.sparta.academy.mfix_mongodb_api.framework.dto.theater;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TheaterDTO {

    @JsonProperty("theaterId")
    private Integer theaterId;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("id")
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

    public boolean hasNonNegativeTheaterId() {
        return theaterId >= 0;
    }

    public <T> boolean isPropertyNull(T element) {
        return element == null;
    }

    public String getPostCode() {
        return location.getAddress().getZipcode();
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
}