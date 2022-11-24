package com.sparta.academy.mfix_mongodb_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.Address;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.Geo;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import com.sparta.academy.mfix_mongodb_api.framework.services.MFlixApplication;
import org.junit.jupiter.api.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TheaterDTOTests {

    @Nested
    class DTOMethodsTests{
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
            assertThat(theaterDTOS[1].hasNonNegativeTheaterId(),is(false));
        }

        @Test
        @DisplayName("Check that latitude less than minus 90 returns false")
        void checkThatLatitudeLessThanMinus90ReturnsFalse() {
            assertThat(theaterDTOS[2].getLocation().getGeo().hasValidCoordinates(),is(false));
        }

        @Test
        @DisplayName("Check that latitude greater than 90 returns false")
        void checkThatLatitudeGreaterThan90ReturnsFalse() {
            assertThat(theaterDTOS[3].getLocation().getGeo().hasValidCoordinates(),is(false));
        }

        @Test
        @DisplayName("Check that longitude less than minus 180 returns false")
        void checkThatLongitudeLessThanMinus180ReturnsFalse() {
            assertThat(theaterDTOS[4].getLocation().getGeo().hasValidCoordinates(),is(false));
        }

        @Test
        @DisplayName("Check that longitude greater than 180 returns false")
        void checkThatLongitudeGreaterThan180ReturnsFalse() {
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

    @Nested
    class HTTPMethodsTests{

        @Nested
        class GETTests{
            private static TheaterDTO dto;

            private static TheaterDTO[] theaterDTOS;
            private static ConnectionResponse response;
            private Geo geo = dto.getLocation().getGeo();
            private Address address = dto.getLocation().getAddress();

            @BeforeAll
            static void setUp()  {
                response = ConnectionManager.from()
                        .baseURL()
                        .slash("theaters")
                        .slash("502")
                        .getResponse();

                dto = response.getBodyAs(TheaterDTO.class);

                theaterDTOS = ConnectionManager.from().baseURL().slash("theaters").slash("all").getResponse().getBodyAsArrayOf(TheaterDTO.class);
            }

            @Test
            @DisplayName("Check that a GET request at all theaters endpoint returns a list of theaters")
            void checkThatAGetRequestAtAllTheatersEndpointReturnsAListOfTheaters() {
                assertThat(theaterDTOS.length,greaterThan(0));
            }

            @Test
            @DisplayName("Check connection response is 200")
            void checkConnectionResponseIs200() {
                assertThat(response.getStatusCode(), is(200));
            }

            @Test
            @DisplayName("Check that the created theater dto is not null")
            void checkThatTheCreatedTheaterDtoIsNotNull() {
                assertThat(dto,notNullValue());
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
        @Nested
        class DELETETests{
            @Test
            @DisplayName("check deleting existing ID returns 200 status")
            void checkDeletingExistingIdReturns200Status() {
                ConnectionResponse response = ConnectionManager.from().baseURL().slash("theaters").slash("1").usingMethod("DELETE").getResponse();

                assertThat(response.getStatusCode(), is(200));
            }
        }

        @Nested
        class POSTTests{
            @Test
            @DisplayName("check posting new theater returns 200 status")
            void checkPostingNewTheaterReturns200Status() {
                ConnectionResponse response = ConnectionManager.from().baseURL().slash("theaters").slash("new").usingMethod("POST").withBody("{\n" +
                        "    \"theaterId\": 1,\n" +
                        "    \"location\": {\n" +
                        "        \"geo\": {\n" +
                        "            \"coordinates\": [\n" +
                        "                -117.674814,\n" +
                        "                33.590599\n" +
                        "            ],\n" +
                        "            \"type\": \"Point\"\n" +
                        "        },\n" +
                        "        \"address\": {\n" +
                        "            \"zipcode\": \"92691\",\n" +
                        "            \"city\": \"Mission Viejo\",\n" +
                        "            \"street1\": \"hghgjgjgj\",\n" +
                        "            \"state\": \"CA\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"id\": \"59a47286dfa9a3a73e51e73b\"\n" +
                        "}").getResponse();

                assertThat(response.getStatusCode(), is(200));


            }
        }

        @Nested
        class PATCHTests{
            @Test
            @DisplayName("check patch method return 200 status")
            void checkPatchMethodReturn200Status() {
                ConnectionResponse response = ConnectionManager.from().baseURL().slash("theaters").slash("953").usingMethod("PATCH").withBody("{\"geo\": {\n" +
                        "                \"coordinates\": [\n" +
                        "                    -93.24565,\n" +
                        "                    44.85466\n" +
                        "                ],\n" +
                        "                \"type\": \"Point\"\n" +
                        "            },\n" +
                        "            \"address\": {\n" +
                        "                \"zipcode\": \"55425\",\n" +
                        "                \"city\": \"Cambridge\",\n" +
                        "                \"street1\": \"340 W Market\",\n" +
                        "                \"state\": \"MN\"\n" +
                        "            }}").getResponse();

                assertThat(response.getStatusCode(), is(200));
            }
        }
    }


}
