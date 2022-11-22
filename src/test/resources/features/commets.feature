@feature
Feature: Testing scenarios for comments endpoint
  Background:
    Given I make a request to the comments API endpoint

  @scenarioAllCommentsGet
  Scenario: Getting a list of all comments
    When I issue a get request for all comments
    Then It should return a list of comments

  @scenarioAllCommentsGetByFilm
  Scenario: Getting a list of all comments by movie
    When I issue a get request for all comments on a movie
    Then It should return a list of comments attached to the movie ID

  @scenarioAllCommentsGetByUserEmail
  Scenario: Getting a list of all comments by each user
    When I issue a get request for all comments made by a user
    Then It should return a list of comments attached to the user email

  @scenarioSingleCommentsGet
  Scenario: Getting a single comments data
    When I issue a get request for a single comments
    Then It should return the post and comments for that specific comments ID

  @scenarioDeleteComments
  Scenario: Deleting a single comments
    When I issue a delete request for a comments
    Then It should remove the comments data

  @scenarioUpdateComments
  Scenario: Updating a single comments
    When I issue a patch request for a comments by comment ID
    Then The comments information should be updated

  @scenarioCreateComments
  Scenario: Creating a single comments
    When I issue a post request for a comments
    Then A new comments will be created
    And check  comments was added to the database

