package com.sparta.academy.mfix_mongodb_api.framework.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TheatreStepdefs {
    @Given("I make a request to the theatre API endpoint")
    public void iMakeARequestToTheTheatreAPIEndpoint() {
    }

    @When("I issue a get request for all theatre")
    public void iIssueAGetRequestForAllTheatre() {
    }

    @Then("It should return a list of theatres")
    public void itShouldReturnAListOfTheatres() {
    }

    @When("I issue a get request for a single theatre by theatreID")
    public void iIssueAGetRequestForASingleTheatreByTheatreID() {
    }

    @Then("It should return the post and comments for that specific theatre")
    public void itShouldReturnThePostAndCommentsForThatSpecificTheatre() {
    }

    @When("I issue a delete request for a theatre by theatre ID")
    public void iIssueADeleteRequestForATheatreByTheatreID() {
    }

    @Then("It should remove the theatre data")
    public void itShouldRemoveTheTheatreData() {
    }

    @When("I issue a patch request for a theatre by theatre ID")
    public void iIssueAPatchRequestForATheatreByTheatreID() {
    }

    @Then("The theatre information should be updated")
    public void theTheatreInformationShouldBeUpdated() {
    }

    @When("I issue a post request for a theatre")
    public void iIssueAPostRequestForATheatre() {
    }

    @Then("A new theatre will be created")
    public void aNewTheatreWillBeCreated() {
    }

    @And("check theatre was added to the database")
    public void checkTheatreWasAddedToTheDatabase() {
    }
}
