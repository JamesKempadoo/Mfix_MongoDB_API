package com.sparta.academy.mfix_mongodb_api.controller_tests;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.MovieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieControllerTester {

    private static MovieDTO dto;
    private static ConnectionResponse response;

    @BeforeAll
    static void setUp()  {

        response = ConnectionManager.from().
                baseURL().
                slash("movies").
                getResponse();

        dto = response.getBodyAs(MovieDTO.class);
        System.out.println(dto.toString());
    }

    @Test
    @DisplayName("Check That date is in correct format")
    void checkThatDateIsInCorrectFormat() {

        Assertions.assertTrue(dto.isDateParseable(dto.getReleased()));
    }
}
