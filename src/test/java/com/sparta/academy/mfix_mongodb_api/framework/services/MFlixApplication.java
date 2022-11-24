package com.sparta.academy.mfix_mongodb_api.framework.services;

import com.sparta.academy.mfix_mongodb_api.MfixMongoDbApiApplication;
import com.sparta.academy.mfix_mongodb_api.controller.CommentsController;
import com.sparta.academy.mfix_mongodb_api.controller.MovieController;
import com.sparta.academy.mfix_mongodb_api.controller.TheaterController;
import com.sparta.academy.mfix_mongodb_api.controller.UserController;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = MfixMongoDbApiApplication.class)
public class MFlixApplication {

    private static SpringRunner runner;

    public static void run() {
        try {
            runner = new SpringRunner(MFlixApplication.class);
            runner.run(new RunNotifier());
        } catch (InitializationError e) {
            System.err.println("Could not initialize application: " + e);
        }
    }

    @Test
    @DisplayName("Spring Application Started")
    public void start() {
    }
}
