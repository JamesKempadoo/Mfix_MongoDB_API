package com.sparta.academy.mfix_mongodb_api.framework.framework_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TheaterDTOTests {

    private static TheaterDTO[] theaterDTOS;

    @BeforeAll
    static void setupAll() {
        ObjectMapper x = new ObjectMapper();
        try {
            theaterDTOS = x.readValue(Paths.get("src/test/resources/TheaterTestJSON.json").toFile(),TheaterDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Check that negative id returns false")
    void checkThatNegativeIdReturnsFalse() {
        //System.out.println(theaterDTOS[1].getTheaterId());
        assertThat(theaterDTOS[1].hasNonNegativeTheaterId(),is(false));
    }

    @Test
    @DisplayName("Check that latitude less than minus 90 returns false")
    void checkThatLatitudeLessThanMinus90ReturnsFalse() {
        //System.out.println(theaterDTOS[2].getLocation().getGeo().getCoordinates());
        assertThat(theaterDTOS[2].getLocation().getGeo().hasValidCoordinates(),is(false));
    }

    @Test
    @DisplayName("Check that latitude greater than 90 returns false")
    void checkThatLatitudeGreaterThan90ReturnsFalse() {
        //System.out.println(theaterDTOS[3].getLocation().getGeo().getCoordinates());
        assertThat(theaterDTOS[3].getLocation().getGeo().hasValidCoordinates(),is(false));
    }

    @Test
    @DisplayName("Check that longitude less than minus 180 returns false")
    void checkThatLongitudeLessThanMinus180ReturnsFalse() {
        //System.out.println(theaterDTOS[4].getLocation().getGeo().getCoordinates());
        assertThat(theaterDTOS[4].getLocation().getGeo().hasValidCoordinates(),is(false));
    }

    @Test
    @DisplayName("Check that longitude greater than 180 returns false")
    void checkThatLongitudeGreaterThan180ReturnsFalse() {
        //System.out.println(theaterDTOS[5].getLocation().getGeo().getCoordinates());
        assertThat(theaterDTOS[5].getLocation().getGeo().hasValidCoordinates(),is(false));
    }

    @Test
    @DisplayName("Check that 3 letter state returns false")
    void checkThat3LetterStateReturnsFalse() {
        assertThat(theaterDTOS[6].getLocation().getAddress().hasValidState(),is(false));
    }

    @Test
    @DisplayName("Check that 5 digit zipcode returns false")
    void checkThat5DigitZipcodeReturnsFalse() {
        assertThat(theaterDTOS[7].getLocation().getAddress().hasValidZipcode(),is(false));
    }

    @Test
    @DisplayName("Check that letter in zipcode returns false")
    void checkThatLetterInZipcodeReturnsFalse() {
        assertThat(theaterDTOS[8].getLocation().getAddress().hasValidZipcode(),is(false));
    }

    @Test
    @DisplayName("Check that when first letter in city is lowercase returns false")
    void checkThatWhenFirstLetterInCityIsLowercaseReturnsFalse() {
        assertThat(theaterDTOS[9].getLocation().getAddress().isCityFirstLetterCapital(),is(false));
    }

    @Test
    @DisplayName("Check that empty street field returns true")
    void checkThatEmptyStreetFieldReturnsTrue() {
        assertThat(theaterDTOS[10].getLocation().getAddress().isPropertyNullOrBlank(theaterDTOS[10].getLocation().getAddress().getStreet1()),is(true));
    }

    @Test
    @DisplayName("Check that null street field returns true")
    void checkThatNullStreetFieldReturnsTrue() {
        assertThat(theaterDTOS[11].getLocation().getAddress().isPropertyNullOrBlank(theaterDTOS[11].getLocation().getAddress().getStreet1()),is(true));
    }
}
