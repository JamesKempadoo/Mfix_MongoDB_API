@feature
Feature: Testing scenarios for movies endpoint
  Background:
    Given I make a request to the movies API endpoint

  @scenarioAllMoviesGet
  Scenario: Getting a list of all movies
    When I issue a get request for all movies
    Then It should return a list of movies

  @scenarioSingleMoviesGet
  Scenario: Getting a single movies data
    When I issue a get request for a single movies by moviesID
    Then It should return the post and comments for that specific movies

  @scenarioDeleteMovies
  Scenario: Deleting a single movies
    When I issue a delete request for a theatre by movies ID
    Then It should remove the movies data

  @scenarioUpdateMovies
  Scenario: Updating a single movies
    When I issue a patch request for a theatre by movies ID
    Then The movies information should be updated

  @scenarioCreateTheatre
  Scenario: Creating a single movies
    When I issue a post request for a movies
    Then A new movies will be created
    And check theatre was added to the movies
