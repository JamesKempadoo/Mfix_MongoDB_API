package com.sparta.academy.mfix_mongodb_api.entity;


public class Tomatoes{
    private Viewer viewer;
    private String lastUpdated;

    public Tomatoes() {
    }

    public Tomatoes(Viewer viewer, String lastUpdated) {
        this.viewer = viewer;
        this.lastUpdated = lastUpdated;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
