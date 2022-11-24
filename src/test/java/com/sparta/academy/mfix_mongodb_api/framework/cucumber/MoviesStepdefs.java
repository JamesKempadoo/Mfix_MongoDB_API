package com.sparta.academy.mfix_mongodb_api.framework.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.MovieDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoviesStepdefs {
    private static ConnectionResponse response;
    private static MovieDTO movie;
    private static String addJsonBody;
    private static String editJsonBody;

    @Given("I make a request to the movies API endpoint")
    public void iMakeARequestToTheMoviesAPIEndpoint() {
        //get json files into String objects for future use
        try {
            addJsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
            editJsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExampleEdited.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ConnectionResponse response = ConnectionManager.from().baseURL().slash("movies").getResponse();
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @When("I issue a get request for all movies")
    public void iIssueAGetRequestForAllMovies() {
        response = ConnectionManager.from().baseURL().slash("movies").getResponse();
    }

    @Then("It should return a list of movies")
    public void itShouldReturnAListOfMovies() {
        MovieDTO[] listOfMovies = response.getBodyAsArrayOf(MovieDTO.class);
        Assertions.assertNotEquals(0, listOfMovies.length);
    }

    @When("I issue a get request for a single movies by moviesID")
    public void iIssueAGetRequestForASingleMoviesByMoviesID() {
        response = ConnectionManager.from().baseURL().slash("movies")
                .slash("573a1398f29313caabce9d84").getResponse();
    }

    @Then("It should return the post and comments for that specific movies")
    public void itShouldReturnThePostAndCommentsForThatSpecificMovies() {
        MovieDTO movie = response.getBodyAs(MovieDTO.class);
        Assertions.assertFalse(movie.isNumMflixCommentsNull());
    }

    @When("I issue a delete request by movies ID")
    public void i_issue_a_delete_request_by_movies_id() {
        ConnectionResponse postResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(addJsonBody).getResponse();
        movie = postResponse.getBodyAs(MovieDTO.class);
        response = ConnectionManager.from().baseURL().slash("movies").slash(movie.getId()).usingMethod("DELETE").getResponse();
    }

    @Then("It should remove the movies data")
    public void itShouldRemoveTheMoviesData() {
        ConnectionResponse checkDeletedResponse = ConnectionManager.from().baseURL().slash("movies").slash(movie.getId()).getResponse();
        Assertions.assertTrue(checkDeletedResponse.getBodyAs(String.class).contains("No Movie with ID"));
    }

    @When("I issue a put request for a movie")
    public void iIssueAPutRequestForAMovie() {
        ConnectionResponse postResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(addJsonBody).getResponse();
        MovieDTO addedMovie = postResponse.getBodyAs(MovieDTO.class);

        response = ConnectionManager.from().baseURL().slash("movies").slash(addedMovie.getId()).usingMethod("PUT").withBody(editJsonBody).getResponse();
        movie = response.getBodyAs(MovieDTO.class);
    }

    @Then("The movies information should be updated")
    public void theMoviesInformationShouldBeUpdated() {
        boolean isChanged = true;

        if(!movie.getTitle().equals("Olympia Part Three: Festival of the Nations")){
            isChanged = false;
        }
        if(!movie.getType().equals("series")){
            isChanged = false;
        }
        if(!movie.getYear().equals("1999")){
            isChanged = false;
        }
        if(!(movie.getRuntime() == 999)){
            isChanged = false;
        }
        if(!movie.getDirectors().contains("Justin")){
            isChanged = false;
        }
        if(!movie.getCountries().contains("UK")){
            isChanged = false;
        }

        Assertions.assertTrue(isChanged);
    }

    @When("I issue a post request for a movies")
    public void iIssueAPostRequestForAMovies() {
        response = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(addJsonBody).getResponse();
        movie = response.getBodyAs(MovieDTO.class);
    }

    @Then("A new movies will be created")
    public void aNewMoviesWillBeCreated() {
        ConnectionResponse checkCreatedResponse = ConnectionManager.from().baseURL().slash("movies").slash(movie.getId()).getResponse();
        Assertions.assertEquals(200, checkCreatedResponse.getStatusCode());
    }

}
