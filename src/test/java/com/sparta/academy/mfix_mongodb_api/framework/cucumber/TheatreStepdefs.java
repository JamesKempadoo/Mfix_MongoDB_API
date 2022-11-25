package com.sparta.academy.mfix_mongodb_api.framework.cucumber;

import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.theater.TheaterDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TheatreStepdefs {

    ConnectionManager connectionRequest;

    TheaterDTO[] theaterDTOS;

    TheaterDTO theaterDTO;

    ConnectionResponse connectionResponse;

    @Given("I make a request to the theatre API endpoint")
    public void iMakeARequestToTheTheatreAPIEndpoint() {
        connectionRequest = ConnectionManager.from().baseURL().slash("theaters");
    }

    @When("I issue a get request for all theatre")
    public void iIssueAGetRequestForAllTheatre() {
        theaterDTOS = connectionRequest.slash("all").usingMethod("GET").getResponse().getBodyAsArrayOf(TheaterDTO.class);
    }

    @Then("It should return a list of theatres")
    public void itShouldReturnAListOfTheatres() {
        assertThat(theaterDTOS.length,greaterThan(0));
    }

    @When("I issue a get request for a single theatre by theatreID")
    public void iIssueAGetRequestForASingleTheatreByTheatreID() {
        theaterDTO = connectionRequest.slash("502").usingMethod("GET").getResponse().getBodyAs(TheaterDTO.class);
    }

    @Then("It should return the data of the specified theater")
    public void itShouldReturnTheDataOfTheSpecifiedTheater() {
        assertThat(theaterDTO,notNullValue());
    }

    @When("I issue a delete request for a theatre by theatre ID")
    public void iIssueADeleteRequestForATheatreByTheatreID() {
        connectionResponse = connectionRequest.slash("101").usingMethod("DELETE").getResponse();

    }

    @Then("It should remove the theatre data")
    public void itShouldRemoveTheTheatreData() {
        assertThat(connectionResponse.getStatusCode(),is(200));
    }

    @When("I issue a patch request for a theatre by theatre ID")
    public void iIssueAPatchRequestForATheatreByTheatreID() {
        connectionResponse = connectionRequest.slash("6").usingMethod("PATCH").withBody("""
                {"geo": {
                            "coordinates": [
                                -82.536293,
                                35.442486
                            ],
                            "type": "Point"
                        },
                        "address": {
                            "zipcode": "28704",
                            "city": "Arden DEDE",
                            "street1": "10 McKenna Rd",
                            "state": "NC"
                        }
                }""").getResponse();
    }

    @Then("The theatre information should be updated")
    public void theTheatreInformationShouldBeUpdated() {
        assertThat(connectionResponse.getStatusCode(),is(200));
    }

    @When("I issue a post request for a theatre")
    public void iIssueAPostRequestForATheatre() {
        connectionResponse = connectionRequest.slash("new").usingMethod("POST").withBody("""
                {
                    "theaterId": 101,
                    "location": {
                        "geo": {
                            "coordinates": [
                                -117.674814,
                                33.590599
                            ],
                            "type": "Point"
                        },
                        "address": {
                            "zipcode": "92691",
                            "city": "Mission Viejo",
                            "street1": "hghgjgjgj",
                            "state": "CA"
                        }
                    },
                    "id": "59a47286dfa9a3a73e51e73b"
                }""").getResponse();
    }

    @Then("A new theatre will be created")
    public void aNewTheatreWillBeCreated() {
        assertThat(connectionResponse.getStatusCode(),is(200));
    }

    @And("check theatre was added to the database")
    public void checkTheatreWasAddedToTheDatabase() {
        theaterDTO = ConnectionManager.from().baseURL().slash("theaters").slash("101").usingMethod("GET").getResponse().getBodyAs(TheaterDTO.class);
        assertThat(theaterDTO,notNullValue());
    }
}
