package com.sparta.academy.mfix_mongodb_api.framework.injector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.exception.InjectorException;

public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getDTO(String json, Class<T> c) {
        try {
            return mapper.readValue(json, c);
        } catch (JsonProcessingException e) {
            throw new InjectorException("Could not map given JSON to DTO. " + json + "\n" + e);
        }
    }
}