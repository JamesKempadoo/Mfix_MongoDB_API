package com.sparta.academy.mfix_mongodb_api.framework.framework_test;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.Address;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.Geo;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TheaterAPITests {

    private static TheaterDTO dto;
    private static ConnectionResponse response;
    private Geo geo = dto.getLocation().getGeo();
    private Address address = dto.getLocation().getAddress();

    @BeforeAll
    static void setUp()  {

        response = ConnectionManager.from()
                .baseURL()
                .slash("theaters")
                .slash("101")
                .getResponse();

        dto = response.getBodyAs(TheaterDTO.class);
        System.out.println(dto.toString());
    }

    @Test
    @DisplayName("Check connection response is 200")
    void checkConnectionResponseIs200() {
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    @DisplayName("Check theaterId is not negative number")
    void checkTheaterIdIsNotNegativeNumber() {
        assertThat(dto.hasNonNegativeTheaterId(), is(true));
    }

    @Test
    @DisplayName("Check street is not null")
    void checkStreetIsNotNull() {
        assertThat(address.isPropertyNullOrBlank(address.getStreet1()), is(false));
    }

    @Test
    @DisplayName("Check state is valid")
    void checkStateIsValid() {
        assertThat(address.hasValidState(), is(true));
    }

    @Test
    @DisplayName("Check coordinates are valid")
    void checkCoordinatesAreValid() {
        assertThat(geo.hasValidCoordinates(), is(true));

    }

    @Test
    @DisplayName("check first letter of city is UppC")//UPPERCASE
    void checkFirstLetterOfCityIsUppC() {
        assertThat(address.isCityFirstLetterCapital(), is(true));
    }

    @Test
    @DisplayName("check zipcode is valid")
    void checkZipcodeIsValid() {
        assertThat(address.hasValidZipcode(), is(true));
    }

    @Test
    @DisplayName("Check general id is not null")
    void checkGeneralIdIsNotNull() {
        assertThat(dto.isPropertyNull(dto.getId()), is(false));
    }

    @Test
    @DisplayName("check location is not null")
    void checkLocationIsNotNull() {
        assertThat(dto.isPropertyNull(dto.getLocation()), is(false));
    }

    @Test
    @DisplayName("check geo type is not null")
    void checkGeoTypeIsNotNull() {
        assertThat(geo.isTypeNull(), is(false));
    }
}
