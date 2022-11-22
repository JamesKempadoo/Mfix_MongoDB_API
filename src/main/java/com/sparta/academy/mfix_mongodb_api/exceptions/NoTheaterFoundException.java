package com.sparta.academy.mfix_mongodb_api.exceptions;

public class NoTheaterFoundException extends Throwable{

    public String getMessage(){
        return "No theater found.";
    }
}
