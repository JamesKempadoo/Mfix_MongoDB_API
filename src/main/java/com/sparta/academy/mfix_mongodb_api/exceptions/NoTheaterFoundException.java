package com.sparta.academy.mfix_mongodb_api.exceptions;

public class NoTheaterFoundException extends RuntimeException{

    public String getMessage(){
        return "No theater found.";
    }
}
