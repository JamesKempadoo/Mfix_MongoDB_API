package com.sparta.academy.mfix_mongodb_api.entity;


public class Tomatoes{
    private Viewer viewer;
    private int lastUpdated;

    public Tomatoes() {
    }

    public Tomatoes(Viewer viewer, int lastUpdated) {
        this.viewer = viewer;
        this.lastUpdated = lastUpdated;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
