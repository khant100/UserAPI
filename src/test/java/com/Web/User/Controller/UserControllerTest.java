package com.Web.User.Controller;

import com.Web.User.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void createUser() throws Exception {

        when(userService.createUser())

    }

    @Test
    void getUser() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}