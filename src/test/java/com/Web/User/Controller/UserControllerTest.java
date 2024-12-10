package com.Web.User.Controller;

import com.Web.User.entity.User;
import com.Web.User.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Mock
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach void setup(){

    }

    @Test
    void createUserPositiveTest() throws Exception {

        User user = new User();
                user.setName("vyas");
                user.setEmail("vyas@outlook.com");
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users").content("""
                {
                "name": "vyas",
                "email":"vyas@outlook.com"
                }""").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("vyas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("vyas@outlook.com"));
    }

    @Test
    void getUserPositiveTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("vyas");
        user.setEmail("vyas@outlook.com");
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",1L)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("vyas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("vyas@outlook.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }
    @Test
    void getUserNegativeTest() throws Exception {

        when(userService.getUserById(99l)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",99L)
                ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void getUserByEmail() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("vyas");
        user.setEmail("vyas@outlook.com");
        User user1 = new User();
        user.setId(1L);
        user.setName("khnat");
        user.setEmail("vyas@outlook.com");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user);

        when(userService.getUserByEmail(any())).thenReturn(Optional.of(users));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/n/{email}","vyas@outlook.com")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).
        andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("vyas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("vyas"));

    }
    @Test
    void getUserByEmailNegativeCase() throws Exception {

        List<User> col = new ArrayList<>();
        when(userService.getUserByEmail("vyas@hotmail")).thenReturn(Optional.of(Collections.EMPTY_LIST));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/n/{email}","vyas@hotmail")
                ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}