package com.sparta.academy.mfix_mongodb_api.framework.framework_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.controller.MovieController;
import com.sparta.academy.mfix_mongodb_api.model.entity.Movie;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.Imdb;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.MovieDTO;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import com.sparta.academy.mfix_mongodb_api.repositories.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MovieDTOTests {

    private static MovieDTO dto;
    private static Imdb imdb;
    private static ConnectionResponse response;
    private static ObjectMapper mapper;

    @BeforeAll
    static void setUp() {

        response = ConnectionManager.from().baseURL().slash("movies").slash("573a1390f29313caabcd50e5").getResponse();

        dto = response.getBodyAs(MovieDTO.class);
        imdb = dto.getImdb();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Check that Connection Response is 200")
    void checkThatConnectionResponseIs200() {

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @DisplayName("Check that Directors is not null")
    void checkThatDirectorsIsNotNull() {

        Assertions.assertFalse(dto.isDirectorsNull());
    }

    @Test
    @DisplayName("Check that Director is not Empty")
    void checkThatDirectorIsNotEmpty() {

        Assertions.assertFalse(dto.isDirectorsEmpty());
    }

    @Test
    @DisplayName("Check that Runtime is not Null")
    void checkThatRuntimeIsNotNull() {

        Assertions.assertFalse(dto.isRunTimeNull());
    }

    @Test
    @DisplayName("Check that Runtime is above 0")
    void checkThatRuntimeIsAbove0() {

        Assertions.assertTrue(dto.isRunTimeAboveZero());
    }

    @Test
    @DisplayName("Check that year is Not empty")
    void checkThatYearIsNotEmpty() {

        Assertions.assertFalse(dto.isYearEmpty());
    }

    @Test
    @DisplayName("Check that year is not Null")
    void checkThatYearIsNotNull() {

        Assertions.assertFalse(dto.isYearNull());
    }

    @Test
    @DisplayName("Check that Year is Equal or Below 2022")
    void checkThatYearIsEqualOrBelow2022() {

        Assertions.assertFalse(dto.isYearAfter2022());
    }

    //countries
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Check that countries is not null or empty")
    void checkCountriesNotNullOrEmpty(List<String> testCountries) {
        Assertions.assertNotEquals(testCountries, dto.getCountries());
    }

    @Test
    @DisplayName("Check that country names only contain letters")
    void checkCountryNamesOnlyLetters() {
        Assertions.assertTrue(dto.isArrayOnlyLetters(dto.getCountries()));
    }

    //title
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Check that title is not null or empty")
    void checkTitleNotNullOrEmpty(String testTitle) {
        Assertions.assertNotEquals(testTitle, dto.getTitle());
    }

    //type
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Check that type is not null or empty")
    void checkTypeNotNullOrEmpty(String testType) {
        Assertions.assertNotEquals(testType, dto.getType());
    }

    @Test
    @DisplayName("Check that type is 'movie' or 'series'")
    void checkTypeIsMovieOrSeries() {
        boolean isTypeCorrect = dto.getType().equals("movie") || dto.getType().equals("series");
        Assertions.assertTrue(isTypeCorrect);
    }

    //poster
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Check that poster is not null or empty")
    void checkPosterNotNullOrEmpty(String testPoster) {
        Assertions.assertNotEquals(testPoster, dto.getPoster());
    }

    @Test
    @DisplayName("Check that poster is a valid link")
    void checkPosterIsValidLink() {
        Assertions.assertTrue(dto.isValidURL(dto.getPoster()));
    }

    //released
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Check that released is not null or empty")
    void checkReleasedNotNullOrEmpty(String testReleased) {
        Assertions.assertNotEquals(testReleased, dto.getReleased());
    }

    @Test
    @DisplayName("Check that released is a valid date")
    void checkReleasedIsValidDate() {
        Assertions.assertTrue(dto.isReleasedDateParseable());
    }

    @Test
    @DisplayName("Check That number of Mflix is null")
    void checkThatNumMflixIsNull() {
        Assertions.assertFalse(dto.isNumMflixCommentsNull());
    }

    @Test
    @DisplayName("Check That number of Mflix is valid")
    void checkThatNumMflixIsValid() {
        Assertions.assertTrue(dto.isNumMflixCommentsValid());
    }

    @Test
    @DisplayName("Check That cast of Mflix is null")
    void checkThatCastIsNull() {
        Assertions.assertFalse(dto.isCastNull());
    }

    @Test
    @DisplayName("Check That cast of Mflix is empty")
    void checkThatCastIsEmpty() {
        Assertions.assertFalse(dto.isTomatoesNull());
    }

    @Test
    @DisplayName("Check that the fullplot is not null")
    void checkThatTheFullPlotIsNotNull() {
        Assertions.assertTrue(dto.getFullplot() != null);
    }

    @Test
    @DisplayName("Check imdb rating is greater than 0")
    void checkimdbRatingGreaterThanZero() {
        Assertions.assertTrue(imdb.getRating() >= 0);
    }

    @Test
    @DisplayName("Check imdb votes is greater than 0")
    void checkimdbvotesGreaterThanZero() {
        Assertions.assertTrue(imdb.getVotes() >= 0);
    }

    @Test
    @DisplayName("Check that the plot is not null")
    void checkThatThePlotIsNotNull() {
        Assertions.assertTrue(dto.getPlot() != null);
    }

    @Test
    @DisplayName("Checking genres is not empty")
    void checkingGenresIsNotEmpty() {
        Assertions.assertNotNull(dto.getGenres());
    }

    @Test
    @DisplayName("Checking lastUpdated is not empty")
    void checkinglastUpdatedIsNotEmpty() {
        Assertions.assertNotNull(dto.getLastupdated());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bird", "New"})
    @DisplayName("Checking that all Movie Title include the word")
    void checkingThatAllMovieTitleIncludeTheWordBird(String str) {

        ConnectionResponse test = ConnectionManager.from().baseURL().slash("movies?title=" + str).getResponse();

        MovieDTO[] movies = test.getBodyAsArrayOf(MovieDTO.class);
        boolean result = true;

        for (MovieDTO movie : movies) {
            if (!movie.getTitle().contains(str)) {
                result = false;
                break;
            }
        }
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("check If Movie is added")
    void checkIfMovieIsAdded() {
        //request body for POST request
        String jsonBody = null;
        try {
            jsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //get total movie count before adding a new one
        ConnectionResponse responseNumberOfMovies = ConnectionManager.from().baseURL().slash("movies").slash("number").getResponse();
        Integer numOfMoviesInRepo = responseNumberOfMovies.getBodyAs(Integer.class);
        //add a new movie
        ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(jsonBody).getResponse();
        //get movie count after adding for comparison
        ConnectionResponse responseNumberOfMovies2 = ConnectionManager.from().baseURL().slash("movies").slash("number").getResponse();
        Integer numOfMoviesInRepo2 = responseNumberOfMovies2.getBodyAs(Integer.class);
        //-1 from new movie count since it should be one higher
        Assertions.assertEquals((int) numOfMoviesInRepo, numOfMoviesInRepo2 - 1);
    }

    @Test
    @DisplayName("check If Movie is deleted")
    void checkIfMovieIsDeleted() {
        //request body for POST request
        String jsonBody = null;
        try {
            jsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //add a temporary movie and get the ID for delete request
        ConnectionResponse testResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(jsonBody).getResponse();
        String testMovieId = testResponse.getBodyAs(MovieDTO.class).getId();
        //get total movie count before adding a new one
        ConnectionResponse responseNumberOfMovies = ConnectionManager.from().baseURL().slash("movies").slash("number").getResponse();
        Integer numOfMoviesInRepo = responseNumberOfMovies.getBodyAs(Integer.class);
        //delete the temporary  movie
        ConnectionResponse responseDeleteMovie = ConnectionManager.from().baseURL().slash("movies").slash(testMovieId).usingMethod("Delete").getResponse();
        //get total movie count after deleting the temporary one
        ConnectionResponse responseNumberOfMovies2 = ConnectionManager.from().baseURL().slash("movies").slash("number").getResponse();
        Integer numOfMoviesInRepo2 = responseNumberOfMovies2.getBodyAs(Integer.class);
        //+1 from new movie count since it should be one lower
        Assertions.assertEquals((int) numOfMoviesInRepo, numOfMoviesInRepo2 + 1);
    }
}
