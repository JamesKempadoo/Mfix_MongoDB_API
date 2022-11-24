package com.sparta.academy.mfix_mongodb_api.framework.framework_test;


import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.Imdb;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.MovieDTO;


import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Random;

public class MovieDTOTests {
    private static MovieDTO[] movieDTOS;
    private static MovieDTO dto;
    private static Imdb imdb;
    private static ConnectionResponse collectionResponse;
    private static ConnectionResponse response;

    @BeforeAll
    static void setUp() {
        //array of movies - GET /movies
        collectionResponse = ConnectionManager.from().baseURL().slash("movies").getResponse();
        movieDTOS = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
        //random movie from the array - GET /movies/{id}
        int randomIndex = new Random().nextInt(movieDTOS.length);
        dto = movieDTOS[randomIndex];
        response = ConnectionManager.from().baseURL().slash("movies").slash(dto.getId()).getResponse();
        dto = response.getBodyAs(MovieDTO.class);
        imdb = dto.getImdb();
    }

    @Nested
    @DisplayName("GET, all Movies Tests")
    class MovieArrayTests{
        @Nested
        @DisplayName("Status Code Tests")
        class StatusCodeTests{
            @Test
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                Assertions.assertEquals(200, collectionResponse.getStatusCode());
            }
        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("Response body Tests")
        class ResponseBodyTests{
            @ParameterizedTest
            @NullAndEmptySource
            @DisplayName("Test that the array is not null or empty")
            void TestArrayNotNullOrEmpty(MovieDTO[] testArray){
                Assertions.assertNotEquals(testArray, movieDTOS);
            }
        }
    }

    @Nested
    @DisplayName("GET, Movie Tests")
    class MovieTests{
        @Nested
        @DisplayName("GET, Status Code Tests")
        class StatusCodeTests {

            @Test
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                Assertions.assertEquals(200, response.getStatusCode());
            }

        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("GET, Response Body Tests")
        class ResponseBodyGetTests {
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
            @Disabled("Unsure what to check here")
            @NullAndEmptySource
            @DisplayName("Check that poster is not null or empty")
            void checkPosterNotNullOrEmpty(String testPoster) {
                Assertions.assertNotEquals(testPoster, dto.getPoster());
            }

            @Test
            @DisplayName("Check that poster is a valid link")
            void checkPosterIsValidLink() {
                Assumptions.assumeTrue(dto.getPoster() != null);
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
                Assumptions.assumeTrue(dto.getReleased() != null);
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
                Assertions.assertFalse(dto.isCastEmpty());
            }

            @Test
            @DisplayName("Check that the fullplot is not null")
            void checkThatTheFullPlotIsNotNull() {
                Assertions.assertNotNull(dto.getFullplot());
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
                Assertions.assertNotNull(dto.getPlot());
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
        }
    }

    @Nested
    @DisplayName("POST, Movie Tests")
    class NewMovieTests{
        private static ConnectionResponse postResponse;
        private static MovieDTO addedMovie;
        @BeforeAll
        static void setup(){
            //request body for POST request
            String jsonBody = null;
            try {
                jsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            postResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(jsonBody).getResponse();
            addedMovie = postResponse.getBodyAs(MovieDTO.class);
        }

        @Nested
        @DisplayName("POST, Status Code Tests")
        class StatusCodeTests {
            @Test
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                Assertions.assertEquals(200, postResponse.getStatusCode());
            }

        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                Assertions.assertEquals("application/json", postResponse.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("POST, Response Body Tests")
        class ResponseBodyPostTests {
            @Test
            @DisplayName("check If Movie is added")
            void checkIfMovieIsAdded() {
                //get movie by id to see if it is added
                ConnectionResponse getResponse = ConnectionManager.from().baseURL().slash("movies").slash(addedMovie.getId()).getResponse();
                MovieDTO addedMovieCheck = getResponse.getBodyAs(MovieDTO.class);
                Assertions.assertEquals(addedMovie.getId(), addedMovieCheck.getId());
            }
        }
    }

    @Nested
    @DisplayName("DELETE, Movie Tests")
    class RemovedMovieTests{
        private static ConnectionResponse deleteResponse;
        private static MovieDTO addedMovie;

        @BeforeAll
        static void setup(){
            //request body for POST request
            String jsonBody = null;
            try {
                jsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ConnectionResponse postResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(jsonBody).getResponse();
            addedMovie = postResponse.getBodyAs(MovieDTO.class);

            deleteResponse = ConnectionManager.from().baseURL().slash("movies").slash(addedMovie.getId()).usingMethod("Delete").getResponse();

        }

        @Nested
        @DisplayName("DELETE, Status Code Tests")
        class StatusCodeTests {
            @Test
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                Assertions.assertEquals(200, deleteResponse.getStatusCode());
            }

        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                Assertions.assertEquals("application/json", deleteResponse.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("DELETE, Response Body Tests")
        class ResponseBodyDeleteTests {
            @Test
            @DisplayName("Check if movie is deleted")
            void checkMovieIsDeleted(){
                ConnectionResponse checkDeletedResponse = ConnectionManager.from().baseURL().slash("movies").slash(addedMovie.getId()).getResponse();
                Assertions.assertTrue(checkDeletedResponse.getBodyAs(String.class).contains("No Movie with ID"));
            }
        }
    }

    @Nested
    @DisplayName("GET, all movies using parameters")
    class MoviesParamArrayTests{
        @Nested
        @DisplayName("GET, all movies using title")
        class MovieTitleArrayTests{
            private static MovieDTO[] movies;
            @BeforeAll
            static void setupAll(){
                ConnectionResponse collectionResponse = ConnectionManager.from().baseURL().slash("movies?title=Bird").getResponse();
                movies = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
            }

            @Nested
            @DisplayName("GET, Status Code Tests")
            class StatusCodeTests {
                @Test
                @DisplayName("Test that status code is 200")
                void TestThatStatusCodeIs200() {
                    Assertions.assertEquals(200, collectionResponse.getStatusCode());
                }

            }

            @Nested
            @DisplayName("Response Header Tests")
            class ResponseHeaderTests {
                @Test
                @DisplayName("Check that content type is application/json")
                void checkContentTypeApplicationJSON(){
                    Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
                }
            }

            @Nested
            @DisplayName("GET, Response Body Tests")
            class ResponseBodyTests {
                @Test
                @DisplayName("Checking that all movie titles contain 'bird'")
                void checkThatMovieTitlesContainBird(){
                    boolean result = true;
                    for (MovieDTO movie : movies) {
                        if (!movie.getTitle().contains("Bird")) {
                            result = false;
                            break;
                        }
                    }
                    Assertions.assertTrue(result);
                }
            }
        }

        @Nested
        @DisplayName("GET, all movies using actor")
        class MovieActorArrayTests{
            private static MovieDTO[] movies;
            @BeforeAll
            static void setupAll(){
                ConnectionResponse collectionResponse = ConnectionManager.from().baseURL().slash("movies?actor=Buster").getResponse();
                movies = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
            }

            @Nested
            @DisplayName("GET, Status Code Tests")
            class StatusCodeTests {
                @Test
                @DisplayName("Test that status code is 200")
                void TestThatStatusCodeIs200() {
                    Assertions.assertEquals(200, collectionResponse.getStatusCode());
                }

            }

            @Nested
            @DisplayName("Response Header Tests")
            class ResponseHeaderTests {
                @Test
                @DisplayName("Check that content type is application/json")
                void checkContentTypeApplicationJSON(){
                    Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
                }
            }

            @Nested
            @DisplayName("GET, Response Body Tests")
            class ResponseBodyTests {
                @Test
                @DisplayName("Checking that all actors arrays contain 'Buster'")
                void checkThatMovieActorsContainBuster(){
                    boolean result = true;
                    for (MovieDTO movie : movies) {
                        if (movie.getCast() != null) {
                            if(movie.getCast().stream().noneMatch(name -> name.contains("Buster"))){
                                result = false;
                                break;
                            }
                        }
                    }
                    Assertions.assertTrue(result);
                }
            }
        }

        @Nested
        @DisplayName("GET, all movies using director")
        class MovieDirectorArrayTests{
            private static MovieDTO[] movies;
            @BeforeAll
            static void setupAll(){
                ConnectionResponse collectionResponse = ConnectionManager.from().baseURL().slash("movies?director=Buster").getResponse();
                movies = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
            }

            @Nested
            @DisplayName("GET, Status Code Tests")
            class StatusCodeTests {
                @Test
                @DisplayName("Test that status code is 200")
                void TestThatStatusCodeIs200() {
                    Assertions.assertEquals(200, collectionResponse.getStatusCode());
                }

            }

            @Nested
            @DisplayName("Response Header Tests")
            class ResponseHeaderTests {
                @Test
                @DisplayName("Check that content type is application/json")
                void checkContentTypeApplicationJSON(){
                    Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
                }
            }

            @Nested
            @DisplayName("GET, Response Body Tests")
            class ResponseBodyTests {
                @Test
                @DisplayName("Checking that all directors arrays contain 'Buster'")
                void checkThatMovieDirectorsContainBuster(){
                    boolean result = true;
                    for (MovieDTO movie : movies) {
                        if (movie.getDirectors() != null) {
                            if(movie.getDirectors().stream().noneMatch(name -> name.contains("Buster"))){
                                result = false;
                                break;
                            }
                        }
                    }
                    Assertions.assertTrue(result);
                }
            }
        }

        @Nested
        @DisplayName("GET, all movies using genre")
        class MovieGenreArrayTests{
            private static MovieDTO[] movies;
            @BeforeAll
            static void setupAll(){
                ConnectionResponse collectionResponse = ConnectionManager.from().baseURL().slash("movies?genre=Horror").getResponse();
                movies = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
            }

            @Nested
            @DisplayName("GET, Status Code Tests")
            class StatusCodeTests {
                @Test
                @DisplayName("Test that status code is 200")
                void TestThatStatusCodeIs200() {
                    Assertions.assertEquals(200, collectionResponse.getStatusCode());
                }

            }

            @Nested
            @DisplayName("Response Header Tests")
            class ResponseHeaderTests {
                @Test
                @DisplayName("Check that content type is application/json")
                void checkContentTypeApplicationJSON(){
                    Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
                }
            }

            @Nested
            @DisplayName("GET, Response Body Tests")
            class ResponseBodyTests {
                @Test
                @DisplayName("Checking that all genre arrays contain 'Horror'")
                void checkThatMovieGenreContainHorror(){
                    boolean result = true;
                    for (MovieDTO movie : movies) {
                        if (movie.getGenres() != null) {
                            if(movie.getGenres().stream().noneMatch(name -> name.contains("Horror"))){
                                result = false;
                                break;
                            }
                        }
                    }
                    Assertions.assertTrue(result);
                }
            }
        }

        @Nested
        @DisplayName("GET, all movies using startYear and endYear")
        class MovieYearArrayTests {
            private static MovieDTO[] movies;

            @BeforeAll
            static void setupAll() {
                ConnectionResponse collectionResponse = ConnectionManager.from().baseURL().slash("movies?startYear=1980&endYear=2000").getResponse();
                movies = collectionResponse.getBodyAsArrayOf(MovieDTO.class);
            }

            @Nested
            @DisplayName("GET, Status Code Tests")
            class StatusCodeTests {
                @Test
                @DisplayName("Test that status code is 200")
                void TestThatStatusCodeIs200() {
                    Assertions.assertEquals(200, collectionResponse.getStatusCode());
                }

            }

            @Nested
            @DisplayName("Response Header Tests")
            class ResponseHeaderTests {
                @Test
                @DisplayName("Check that content type is application/json")
                void checkContentTypeApplicationJSON() {
                    Assertions.assertEquals("application/json", collectionResponse.getHeader("Content-Type"));
                }
            }

            @Nested
            @DisplayName("GET, Response Body Tests")
            class ResponseBodyTests {
                @Test
                @DisplayName("Checking that all movies year is between 1980 and 2000")
                void checkThatMovieYearBetweenDates() {
                    boolean result = true;
                    for (MovieDTO movie : movies) {
                        int year = Integer.parseInt(movie.getYear());
                        if (!(year >= 1980 && year <= 2000)) {
                            result = false;
                            break;
                        }
                    }
                    Assertions.assertTrue(result);
                }
            }
        }
    }

    @Nested
    @DisplayName("PUT, Movie Tests")
    class EditMovieTests{
        private static ConnectionResponse putResponse;
        private static MovieDTO editedMovieCheck;
        @BeforeAll
        static void setup(){
            //request body for PUT request
            String addJsonBody = null;
            String editJsonBody = null;
            try {
                addJsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExample.json"));
                editJsonBody = Files.readString(Paths.get("src/test/resources/MovieResponseExampleEdited.json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ConnectionResponse postResponse = ConnectionManager.from().baseURL().slash("movies").usingMethod("POST").withBody(addJsonBody).getResponse();
            MovieDTO addedMovie = postResponse.getBodyAs(MovieDTO.class);

            putResponse = ConnectionManager.from().baseURL().slash("movies").slash(addedMovie.getId()).usingMethod("PUT").withBody(editJsonBody).getResponse();
            MovieDTO editedMovie = putResponse.getBodyAs(MovieDTO.class);

            ConnectionResponse getResponse = ConnectionManager.from().baseURL().slash("movies").slash(editedMovie.getId()).getResponse();
            editedMovieCheck = getResponse.getBodyAs(MovieDTO.class);
        }

        @Nested
        @DisplayName("PUT, Status Code Tests")
        class StatusCodeTests {
            @Test
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                Assertions.assertEquals(200, putResponse.getStatusCode());
            }

        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                Assertions.assertEquals("application/json", putResponse.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("PUT, Response Body Tests")
        class ResponseBodyPostTests {
            @Test
            @DisplayName("check If Movie Title was changed")
            void checkIfMovieTitleChanged() {
                Assertions.assertEquals("Olympia Part Three: Festival of the Nations", editedMovieCheck.getTitle());
            }

            @Test
            @DisplayName("check If Movie Type was changed")
            void checkIfMovieTypeChanged() {
                Assertions.assertEquals("series", editedMovieCheck.getType());
            }

            @Test
            @DisplayName("check If Movie Year was changed")
            void checkIfMovieYearChanged() {
                Assertions.assertEquals("1999", editedMovieCheck.getYear());
            }

            @Test
            @DisplayName("check If Movie Runtime was changed")
            void checkIfMovieRuntimeChanged() {
                Assertions.assertEquals(999, editedMovieCheck.getRuntime());
            }

            @Test
            @DisplayName("check If Movie Directors was changed")
            void checkIfMovieDirectorsChanged() {
               Assertions.assertTrue(editedMovieCheck.getDirectors().contains("Justin"));
            }

            @Test
            @DisplayName("check If Movie Countries was changed")
            void checkIfMovieCountriesChanged() {
                Assertions.assertTrue(editedMovieCheck.getCountries().contains("UK"));
            }
        }
    }


}
