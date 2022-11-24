package com.sparta.academy.mfix_mongodb_api.framework.services;

import com.sparta.academy.mfix_mongodb_api.MfixMongoDbApiApplication;
import com.sparta.academy.mfix_mongodb_api.controller.CommentsController;
import com.sparta.academy.mfix_mongodb_api.controller.MovieController;
import com.sparta.academy.mfix_mongodb_api.controller.TheaterController;
import com.sparta.academy.mfix_mongodb_api.controller.UserController;
import com.sparta.academy.mfix_mongodb_api.repositories.CommentRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.MovieRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.TheaterRepository;
import com.sparta.academy.mfix_mongodb_api.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = MfixMongoDbApiApplication.class)
public class MockMFlixApplication {

    private static SpringRunner runner;

    public static void run() {
        try {
            runner = new SpringRunner(MockMFlixApplication.class);
            runner.run(new RunNotifier());
        } catch (InitializationError e) {
            System.err.println("Could not initialize application: " + e);
        }
    }

    @InjectMocks
    private static CommentsController commentsController;
    @InjectMocks
    private static MovieController movieController;
    @InjectMocks
    private static UserController userController;
    @InjectMocks
    private static TheaterController theaterController;

    @MockBean
    private static CommentRepository commentRepository;
    @MockBean
    private static MovieRepository movieRepository;
    @MockBean
    private static UserRepository userRepository;
    @MockBean
    private static TheaterRepository theaterRepository;

    @Test
    @DisplayName("Spring Application Started")
    public void start() {
    }

    public static void resetRepositoryMocks() {
        Mockito.reset(commentRepository);
        Mockito.reset(movieRepository);
        Mockito.reset(userRepository);
        Mockito.reset(theaterRepository);
    }

    public static CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public static MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static TheaterRepository getTheaterRepository() {
        return theaterRepository;
    }
}
