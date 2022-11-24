@feature
Feature: Testing scenarios for theatre endpoint
  Background:
    Given I make a request to the theatre API endpoint

  @scenarioAllTheatreGet
  Scenario: Getting a list of all theatre
    When I issue a get request for all theatre
    Then It should return a list of theatres

  @scenarioSingleTheatreGet
  Scenario: Getting a single theatre data
    When I issue a get request for a single theatre by theatreID
    Then It should return the data of the specified theater

  @scenarioDeleteTheatre
  Scenario: Deleting a single theatre
    When I issue a delete request for a theatre by theatre ID
    Then It should remove the theatre data

  @scenarioUpdateTheatre
  Scenario: Updating a single theatre
    When I issue a patch request for a theatre by theatre ID
    Then The theatre information should be updated

  @scenarioCreateTheatre
  Scenario: Creating a single theatre
    When I issue a post request for a theatre
    Then A new theatre will be created
    And check theatre was added to the database
