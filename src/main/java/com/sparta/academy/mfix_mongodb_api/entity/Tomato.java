package com.sparta.academy.mfix_mongodb_api.entity;


public class Tomato {
    private Viewer viewer;
    private String lastUpdated;

    public Tomato() {
    }

    public Tomato(Viewer viewer, String lastUpdated) {
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
