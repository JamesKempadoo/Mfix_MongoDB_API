package com.sparta.academy.mfix_mongodb_api.framework.framework_test;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.UserCollectionDTO;
import com.sparta.academy.mfix_mongodb_api.framework.dto.UserDTO;
import org.assertj.core.util.ArrayWrapperList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserFrameworkTest {

    //private static UserCollectionDTO collectionDTO;
    private static UserDTO[] users;
    private static UserDTO dto;
    private static ConnectionResponse collectionResponse;
    private static ConnectionResponse response;

    @BeforeAll
    static void setupAll() {
        collectionResponse = from().baseURL().slash("users").getResponse();
        response = from().baseURL().slash("users").slash("59b99db6cfa9a34dcd7885bb").getResponse();

        users = collectionResponse.getBodyAs(UserDTO[].class);
        dto = response.getBodyAs(UserDTO.class);
    }

    @Nested
    @DisplayName("User Tests")
    class UserTests {

        @Nested
        @DisplayName("Status Code Tests")
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
        class ResponseBodyTests {
            @Test
            @DisplayName("Test that name is not empty")
            void TestThatNameIsNotEmptyString() {
                assertThat(dto.getName(), is(not(emptyString())));
            }

            @Test
            @DisplayName("Test that name is not null")
            void TestThatNameIsNotNull() {
                assertThat(dto.getName(), is(notNullValue()));
            }
        }

    }


}
