package com.sparta.academy.mfix_mongodb_api.framework.connection;

import com.sparta.academy.mfix_mongodb_api.framework.exception.ConnectionManagementException;
import com.sparta.academy.mfix_mongodb_api.framework.injector.Injector;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionResponse {

    private HttpResponse<String> response;

    ConnectionResponse() {
    }

    public ConnectionResponse makeRequest(String URL) throws ConnectionManagementException {
        HttpResponse<String> response = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IllegalArgumentException e) {
            throw new ConnectionManagementException("Given URL, " + URL + ", is not a valid URL");
        } catch (IOException | InterruptedException e) {
            throw new ConnectionManagementException("Request could not be made: " + e.getMessage());
        }
        this.response = response;
        return this;
    }

    public ConnectionResponse and() {
        return this;
    }

    public String getHeader(String key) {
        return response
                .headers()
                .firstValue(key)
                .orElse("Header not found.");
    }

    public int getStatusCode() {
        return response.statusCode();
    }

    public <T> T getBodyAs(Class<T> dtoClass) {
        try {
            if (dtoClass == String.class) {
                return (T) response.body();
            } else {
                return Injector.getDTO(response.body(), dtoClass);
            }
        } catch (ClassCastException e) {
            throw new ConnectionManagementException("Could not get body as type " + dtoClass.getName());
        }
    }

    public <T> T[] getBodyAsArrayOf(Class<T> dtoClass) {
        try {
            if (dtoClass == String.class) {
                String[] arr = {response.body()};
                return (T[]) arr;
            } else {
                return (T[]) Injector.getDTO(response.body(), dtoClass.arrayType());
            }
        } catch (ClassCastException e) {
            throw new ConnectionManagementException("Could not get body as type " + dtoClass.getName());
        }
    }

}