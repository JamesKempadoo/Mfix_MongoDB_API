# Mflix MongoDB API
**Developed by <ins>Sparta Engineering Academy</ins>: 
Riya, 
Max,
Justin,
Abdulhadi,
Fransico,
Calum,
Gurjeev,
Igran,
Chung,
Ioannis,
James,
Lucas,
Ricardo,
Will,
Michalis,
John,
Maria,
Hamzah,
Mohamed.**

### **Table of Contents**
* [**About Project**](#about-project)
    * [Built with](#built-with)
    * [Dependencies](#dependencies)
* [**Requirements**](#requirements)
* [**Getting Started**](#getting-started)
* [**BDD And Cucumber**](#bdd-and-cucumber)

## About Project


This project is developed as a team of 19, building a RESTful API connected to a Mongo database that allows for CRUD operations requested by the user. The structure of the 
RESTful API uses MVC model and Swagger is used to display the API endpoint requests.
Also creating a testing framework structure (Service Object Model), using Jackson,
BDD Development and Cucumber, Rest-Assured, implementing JUnit and Hamcrest Tests, as well as using mocking to test the framework. We also
made use of a singleton logger to log the classes.

The project's functionality is using a MongoDB and cloud-based solution for accessing information about movies,
allowing users to post or read comments on movies and managing a film schedule for theaters. Also building a testing framework to test the MongoDB API for testers to use.




### <span style="color: blue;">**Built With**</span>

* IntelliJ IDEA (Ultimate Edition)
* Spring
* Maven
### <span style="color: blue;">**Dependencies**</span>

* spring-boot-starter-web
* spring-boot-starter-data-mongodb
* spring-boot-starter-hateoas
* springdoc-openapi-ui
* spring-boot-starter-test
* cucumber-java
* cucumber-junit
* junit-vintage-engine

## Requirements

* To build a RESTful API which allows full CRUD access to the existing data for these 4 collections
* The [sample database (sample_mflix)](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/) which includes details of movies, comments, "theaters" and users.
* Create an API testing framework which checks the REST service is working and provides a BDD layer



## Getting Started

Clone the repository
```
git clone https://github.com/JamesKempadoo/Mfix_MongoDB_API.git
```
Check Maven dependancies are installed

Edit application.properties with your DB URI
```
spring.data.mongodb.uri=mongodb+srv://<user>:<pass>@yourCluster.mongodb.net/?retryWrites=true&w=majority
spring.data.mongodb.database=sample_mflix
database.uri=mongodb+srv://<user>:<pass>@yourCluster.mongodb.net/sample_mflix
```
Run application via MfixMongoDbApiApplication.java
# Program Structure
## RESTful API Structure

* **controller**
  * CommentsController
  * MovieController
  * SessionsController
  * TheaterController
  * UserController
  
* **exceptions**
  * ControllerExceptionHandler
  * NoTheaterFoundException
  * ResourceException
  * IDNotFoundException
 
* **logging**
  * CustomConsoleHandler
  * CustomFileHandler 
  * CustomFilter
  * CustomFormatter
  * CustomLoggerConfiguration
  * Log
  * LoggerSingleton
  * LoggingExample

* **model**
  * DBConfig
  * MongoConfig
  
* **entity**
  * Comment
  * imdb
  * Movie
  * Theater
  * Tomato
  * User
  * Viewer

* **theater**
  * Address
  * Geo
  * Location
  * Theater
  
* **repositories**
  * CommentRepository
  * MovieRepository
  * TheaterRepository 
  * UserRepository 

* **connection**
    * ConnectionManager
    * ConnectionResponse
  
* **Cucumber**
  * CommentStepdefs
  * MovieStepdefs
  * TestRunner
  * TheatreStepdefs
  * UserStepdefs

* **dto**
* **comments**
  * CommentsDTO 

* **movie**
  * imdb
  * MovieDTO
  * Tomatoes
  * Viewer

* **theater**
  * Address
  * Geo
  * Location
  * TheaterDTO

* **User**
  * UserDTO

* **Exceptions**
  * ConnectionManagementException
  * InjectorException

* **framework_test**
  * FrameworkTest
  * TheaterDTOTest
  * MovieDTOTest
  * UserDTOTest

* **controller_test**
  * UserControllerTest 

* **injection**
  * Injector

* **services**
  * Services

# BDD And Cucumber
## Feature File for Movies

![feauture_file](https://github.com/JamesKempadoo/Mfix_MongoDB_API/blob/updatedreadme/programscreenshots/feature.png)

## Stepdefs File for Feature File of Movies

![stepdefs_file](https://github.com/JamesKempadoo/Mfix_MongoDB_API/blob/updatedreadme/programscreenshots/stepdefs.png)

## HTML File on Movies Features Tested on Cucumber

![html_file](https://github.com/JamesKempadoo/Mfix_MongoDB_API/blob/updatedreadme/programscreenshots/htmlfile.png)
