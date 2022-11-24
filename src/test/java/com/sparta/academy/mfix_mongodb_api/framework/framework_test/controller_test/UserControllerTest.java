package com.sparta.academy.mfix_mongodb_api.framework.framework_test.controller_test;

import com.sparta.academy.mfix_mongodb_api.controller.UserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    private UserController controller;


    UserControllerTest(UserController controller) {
        this.controller = controller;
    }

    @Test
    @Transactional
    void deleteTest() {
        ResponseEntity<String> response = controller.deleteUser("59b99db6cfa9a34dcd7885bb");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
