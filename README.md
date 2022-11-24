# Mfix_MongoDB_API
**Developed by <ins>Sparta Engineering Academy</ins>: 
Riya, 
Max,
Justin,
Abdulhadi,
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
* [**Endpoint Testing Examples**](#endpoints)
    * [Without Framework](#without-framework)
    * [With Framework](#with-framework)
* [**BDD And Cucumber**](#bdd-and-cucumber)

## About Project

This project is developed as a team of 18, building a restful API connected to a mongo database that allows for CRUD operations requested by the user. The structure of the 
rest API uses MVC model and Swagger is used to display the API endpoint requests.
Also creating a testing framework structure (Service Object Model), using Jackson,
BDD Development and Cucumber, Rest-Assured, implementing JUnit and Hamcrest Tests, as well as using mocking to test the framework. We also
made use of a singleton logger to log the classes.

The project's functionality is using a MongoDB and cloud-based solution for accessing information about movies,
allowing users to post or read comments on movies and managing a film schedule for cinemas. Also building a testing framework to test the MongoDB API for testers to use.

### <span style="color: blue;">**Built With**</span>

* IntelliJ IDEA (Ultimate Edition)

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
* The mongo DB contains sample database (sample_mflix) which includes details of movies, comments, "theaters" and users.
* Create an API testing framework which checks the REST service is working and provides a BDD layer



## Getting Started

Run the project using Ultimate Edition.
Make sure to install the dependencies and software included.

Clone the repository below.
```
git clone https://github.com/JamesKempadoo/Mfix_MongoDB_API.git
```

# Program Structure
## Restful API Structure

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
    * Connection Manager
    * Connection Response
  
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

# Endpoint Testing Examples



# BDD And Cucumber


## Feature File for Planets

![feauture_file](https://github.com/MRobertsSparta/SWAPITestingFramework/blob/dev/programscreenshots/featurefile.png)

## Stepdefs File for Feature File of Planets

![stepdefs_file](https://github.com/MRobertsSparta/SWAPITestingFramework/blob/dev/programscreenshots/stepdefs.png)

## HTML File on Planets Features Tested on Cucumber

![html_file](https://github.com/MRobertsSparta/SWAPITestingFramework/blob/dev/programscreenshots/htmlfile.png)