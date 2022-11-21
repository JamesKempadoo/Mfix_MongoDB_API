package com.sparta.academy.mfix_mongodb_api.framework.exception;

public class InjectorException extends RuntimeException {

    private String message;

    public InjectorException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
