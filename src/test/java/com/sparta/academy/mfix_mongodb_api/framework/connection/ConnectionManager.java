package com.sparta.academy.mfix_mongodb_api.framework.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.exception.ConnectionManagementException;
import com.sun.jdi.ObjectReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;

public class ConnectionManager {

    private static int port = 8080;
    public static final String BASE_URL = "http://localhost:" + port;

    private String URL;
    private HashMap<String, String> parameters;
    private String body;
    private String method;

    private ConnectionManager() {
        this.URL = BASE_URL;
        this.body = "";
        this.method = "GET";
        this.parameters = new HashMap<>();
    }

    public static ConnectionManager from() {
        return new ConnectionManager();
    }

    public ConnectionManager URL(String URL) {
        this.URL = URL;
        return this;
    }

    public ConnectionManager baseURL() {
        return URL(BASE_URL);
    }

    public ConnectionManager slash(String endpoint) {
        if (endpoint.startsWith("/") && URL.endsWith("/")) {
            endpoint = endpoint.substring(1);
        } else if (!endpoint.startsWith("/") && !URL.endsWith("/")) {
            endpoint = "/" + endpoint;
        }
        URL += endpoint;
        return this;
    }

    public ConnectionManager withParameter(String parameter, String value) {
        parameters.put(parameter, value);
        return this;
    }

    public ConnectionManager withParameter(String parameter, int value) {
        return withParameter(parameter, "" + value);
    }

    public ConnectionManager withParameter(String parameter, float value) {
        return withParameter(parameter, "" + value);
    }

    public ConnectionManager withBody(String body) {
        this.body = body;
        return this;
    }

    public <T> ConnectionManager withBody(T body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.body = mapper.writeValueAsString(body);
            return this;
        } catch (JsonProcessingException e) {
            throw new ConnectionManagementException("Could not create JSON from given body. " + e.getMessage());
        }
    }

    public ConnectionManager usingMethod(String method) {
        this.method = method.toUpperCase();
        return this;
    }

    private String buildURL() {
        String finalURL = URL;
        if (finalURL.endsWith("/")) {
            finalURL.replaceAll("\\/$", "");
        }

        for (String key : parameters.keySet()) {
            if (finalURL.contains("?")) {
                finalURL += "&";
            } else {
                finalURL += "?";
            }
            finalURL += key + "=" + parameters.get(key);
        }
        return finalURL;
    }

    public ConnectionResponse getResponse() {
        return new ConnectionResponse().makeRequest(buildURL(), method, body);
    }

    public String getURL() {
        return URL;
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }
}