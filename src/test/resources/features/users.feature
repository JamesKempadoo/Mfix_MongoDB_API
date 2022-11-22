@feature
Feature: Testing scenarios for users endpoint
  Background:
    Given I make a request to the users API endpoint

    @scenarioAllUsersGet
    Scenario: Getting a list of all users
      When I issue a get request for all users
      Then It should return a list of users

    @scenarioSingleUserGet
    Scenario: Getting a single users data
      When I issue a get request for a single user
      Then It should return the post and comments for that specific user

    @scenarioDeleteUser
    Scenario: Deleting a single user
      When I issue a delete request for a user
      Then It should remove the users data

    @scenarioUpdateUser
    Scenario: Updating a single user
      When I issue a patch request for a user
      Then The users information should be updated

    @scenarioCreateUser
    Scenario: Creating a single user
      When I issue a post request for a user
      Then A new user will be created
      And check user was added to the database

