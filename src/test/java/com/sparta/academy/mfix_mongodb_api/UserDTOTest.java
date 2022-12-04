package com.sparta.academy.mfix_mongodb_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import com.sparta.academy.mfix_mongodb_api.framework.dto.user.UserDTO;
import org.junit.jupiter.api.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;

import static com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {

    private static UserDTO[] users;
    private static UserDTO dto;
    private static ConnectionResponse collectionResponse;
    private static ConnectionResponse response;

    private static UserDTO[] usersJSON;

    @BeforeAll
    static void setupAll() {
        collectionResponse = from().baseURL().slash("users").getResponse();
        response = from().baseURL().slash("users").slash("59b99db4cfa9a34dcd7885b7").getResponse();

        users = collectionResponse.getBodyAs(UserDTO[].class);
        dto = response.getBodyAs(UserDTO.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            usersJSON = objectMapper.readValue(Paths.get("src/test/resources/UserTestJSON.json").toFile(), UserDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void refreshDB() {
        from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[0]).getResponse();

        from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[1]).getResponse();

        from().baseURL().slash("users").usingMethod("PATCH").withBody(usersJSON[2]).getResponse();

        from().baseURL().slash("users").slash(usersJSON[3].getId()).usingMethod("DELETE").getResponse();
        from().baseURL().slash("users").slash(usersJSON[4].getId()).usingMethod("DELETE").getResponse();
    }

    @Nested
    @DisplayName("All Users Tests")
    class UserArrayTests {

        @Nested
        @DisplayName("Status Code Tests")
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
        class ResponseBodyTests {

            @Test
            @DisplayName("Test that users array is not null")
            void TestThatUserArrayIsNotNull() {
                assertNotNull(users);
            }

        }

    }

    @Nested
    @DisplayName("GET, User Tests")
    class UserTests {

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
                Assertions.assertEquals("application/json", response.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("GET, Response Body Tests")
        class ResponseBodyGetTests {
            @Test
            @DisplayName("Test that name is not blank")
            void TestThatNameIsNotBlank() {
                assertFalse(dto.isNameBlank());
            }

            @Test
            @DisplayName("Test that name is not null")
            void TestThatNameIsNotNull() {
                assertFalse(dto.isNameNull());
            }

            @Test
            @DisplayName("Test that id is not blank")
            void TestThatIdIsNotBlank() {
                assertFalse(dto.isIdBlank());
            }

            @Test
            @DisplayName("Test that id is not null")
            void TestThatIdIsNotNull() {
                assertFalse(dto.isIdNull());
            }

            @Test
            @DisplayName("Test that password is not blank")
            void TestThatPasswordIsNotBlank() {
                assertFalse(dto.isPasswordBlank());
            }

            @Test
            @DisplayName("Test that password is not null")
            void TestThatPasswordIsNotNull() {
                assertFalse(dto.isPasswordNull());
            }

            @Test
            @DisplayName("Test that encrypted password length is 60")
            void TestThatPasswordLengthIs60() {
                assertTrue(dto.isPasswordEncryptionValidLength());
            }

            @Test
            @DisplayName("Test that email is not blank")
            void TestThatEmailIsNotBlank() {
                assertFalse(dto.isEmailBlank());
            }

            @Test
            @DisplayName("Test that email is not null")
            void TestThatEmailIsNotNull() {
                assertFalse(dto.isEmailNull());
            }
        }

    }

    @Nested
    @DisplayName("DELETE, User tests")
    class UserDeleteTests {

        // WHEN a user is deleted THEN the http status response is 200 SO THAT the requester knows that the call succeeded
        // WHEN a user that does not exist is deleted THEN the http status response is 400 and the response body should be a json error telling the user that the user does not exist SO THAT the caller is aware of the user not existing

        // WHEN a user is deleted THEN getting a list of users with the same id should return an empty collection SO THAT the users data is ensured to have been removed


        @Nested
        @DisplayName("DELETE, Status Code Tests")
        class StatusCodeTests {

            @Test
            @Transactional
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                ConnectionResponse response = from().baseURL().slash("users").slash(usersJSON[0].getId()).usingMethod("DELETE").getResponse();
                Assertions.assertEquals(200, response.getStatusCode());
            }

            @Test
            @Transactional
            @DisplayName("Test that status code is 400")
            void TestThatStatusCodeIs400() {
                ConnectionResponse response = from().baseURL().slash("users").slash("59b99db6cfa9a34dcd7885bbafdg").usingMethod("DELETE").getResponse();
                Assertions.assertEquals(400, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @Transactional
            @DisplayName("Check that content type is text/plain;charset=UTF-8")
            void checkContentTypeApplicationJSON(){
                ConnectionResponse response = from().baseURL().slash("users").slash(usersJSON[1].getId()).usingMethod("DELETE").getResponse();
                Assertions.assertEquals("text/plain;charset=UTF-8", response.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("Response Tests")
        class ResponseTests {

        }
    }

    @Nested
    @DisplayName("POST, User tests")
    class UserPostTests {
        @Nested
        @DisplayName("POST, Status Code Tests")
        class StatusCodeTests {
            @Test
            @Transactional
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[3]).getResponse();
                Assertions.assertEquals(200, response.getStatusCode());
            }

            @Test
            @Transactional
            @DisplayName("Test that status code is 400")
            void TestThatStatusCodeIs400() {
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("POST").getResponse();
                Assertions.assertEquals(400, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @Transactional
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[4]).getResponse();
                Assertions.assertEquals("application/json", response.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("Response Tests")
        class ResponseTests {

        }
    }


    @Nested
    @DisplayName("PATCH, User tests")
    class UserPatchTests {
        @Nested
        @DisplayName("PATCH, Status Code Tests")
        class StatusCodeTests {
            @Test
            @Transactional
            @DisplayName("Test that status code is 200")
            void TestThatStatusCodeIs200() {
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("PATCH").withBody(usersJSON[5]).getResponse();
                Assertions.assertEquals(200, response.getStatusCode());
            }

            @Test
            @Transactional
            @DisplayName("Test that status code is 400")
            void TestThatStatusCodeIs400() {
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("PATCH").getResponse();
                Assertions.assertEquals(400, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("Response Header Tests")
        class ResponseHeaderTests {
            @Test
            @Transactional
            @DisplayName("Check that content type is application/json")
            void checkContentTypeApplicationJSON(){
                ConnectionResponse response = from().baseURL().slash("users").usingMethod("PATCH").withBody(usersJSON[6]).getResponse();
                Assertions.assertEquals("application/json", response.getHeader("Content-Type"));
            }
        }

        @Nested
        @DisplayName("Response Tests")
        class ResponseTests {

        }
    }
}