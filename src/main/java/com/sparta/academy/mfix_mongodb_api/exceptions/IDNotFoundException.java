package com.sparta.academy.mfix_mongodb_api.exceptions;

public class IDNotFoundException extends Exception {
    private final int statusCode;
    private final String path;
    private final String message;

    public IDNotFoundException(int statusCode, String path, String message) {
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
