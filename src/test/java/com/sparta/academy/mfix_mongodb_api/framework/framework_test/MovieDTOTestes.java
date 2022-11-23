package com.sparta.academy.mfix_mongodb_api.framework.framework_test;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.movie.MovieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieDTOTestes {

    private static MovieDTO dto;
    private static ConnectionResponse response;

    @BeforeAll
    static void setUp()  {

        response = ConnectionManager.from()
                .baseURL()
                .slash("movies")
                .slash("573a1390f29313caabcd50e5")
                .getResponse();

        dto = response.getBodyAs(MovieDTO.class);
        System.out.println(dto.toString());
    }

    @Test
    @DisplayName("Check that Connection Response is 200")
    void checkThatConnectionResponseIs200() {

        Assertions.assertEquals(200,response.getStatusCode());
    }
}
