package com.sparta.academy.mfix_mongodb_api.framework.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionResponse;
import com.sparta.academy.mfix_mongodb_api.framework.dto.user.UserDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;

import static com.sparta.academy.mfix_mongodb_api.framework.connection.ConnectionManager.from;
import static org.junit.jupiter.api.Assertions.*;

public class UserStepdefs {
    private static UserDTO[] users;
    private static UserDTO dto;
    private static ConnectionResponse collectionResponse;
    private static ConnectionResponse response;

    private static UserDTO[] usersJSON;



    @Given("I make a request to the users API endpoint")
    public void iMakeARequestToTheUsersAPIEndpoint() {
        collectionResponse = from().baseURL().slash("users").getResponse();
        response = from().baseURL().slash("users").slash("59b99db4cfa9a34dcd7885b7").getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            usersJSON = objectMapper.readValue(Paths.get("src/test/resources/UserTestJSON.json").toFile(), UserDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[0]).getResponse();

        from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[1]).getResponse();

        from().baseURL().slash("users").usingMethod("PATCH").withBody(usersJSON[2]).getResponse();

        from().baseURL().slash("users").slash(usersJSON[3].getId()).usingMethod("DELETE").getResponse();
        from().baseURL().slash("users").slash(usersJSON[4].getId()).usingMethod("DELETE").getResponse();
    }

    @When("I issue a get request for all users")
    public void iIssueAGetRequestForAllUsers() {
        users = collectionResponse.getBodyAs(UserDTO[].class);
    }

    @Then("It should return a list of users")
    public void itShouldReturnAListOfUsers() {
        assertNotNull(users);
    }

    @When("I issue a get request for a single user")
    public void iIssueAGetRequestForASingleUser() {
        dto = response.getBodyAs(UserDTO.class);
    }

    @Then("It should return that specific user")
    public void itShouldReturnThatSpecificUser() {
        assertEquals("59b99db4cfa9a34dcd7885b7", dto.getId());
    }

    @When("I issue a delete request for a user")
    public void iIssueADeleteRequestForAUser() {
       response = from().baseURL().slash("users").slash(usersJSON[0].getId()).usingMethod("DELETE").getResponse();
    }

    @Then("It should remove the users data")
    public void itShouldRemoveTheUsersData() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @When("I issue a patch request for a user")
    public void iIssueAPatchRequestForAUser() {
        response = from().baseURL().slash("users").usingMethod("PATCH").withBody(usersJSON[5]).getResponse();
    }

    @Then("The users information should be updated")
    public void theUsersInformationShouldBeUpdated() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @When("I issue a post request for a user")
    public void iIssueAPostRequestForAUser() {
        response = from().baseURL().slash("users").usingMethod("POST").withBody(usersJSON[3]).getResponse();
    }

    @Then("A new user will be created")
    public void aNewUserWillBeCreated() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @And("check user was added to the database")
    public void checkUserWasAddedToTheDatabase() {
    }
}
