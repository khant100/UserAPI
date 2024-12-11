package com.Web.User.service;

import com.Web.User.Repository.UserRepository;
import com.Web.User.entity.User;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




@SpringBootTest
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("vyas");
        user.setEmail("vyas@gmail.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(user);
        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(2L, createdUser.getId());
        assertEquals("vyas", createdUser.getName());
        assertEquals("vyas@gmail.com", createdUser.getEmail());

    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(2L);
        user.setName("vyas");
        user.setEmail("vyas@gmail.com");

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));


        Optional<User> getByid = userService.getUserById(2L);

        assertNotNull(getByid);
        assertEquals(2L, getByid.get().getId());
        assertEquals("vyas", getByid.get().getName());
        assertEquals("vyas@gmail.com", getByid.get().getEmail());
    }

    @Test
    void getUserByEmail() {
        User user = new User();
        user.setId(2L);
        user.setName("vyas");
        user.setEmail("vyas@gmail.com");
        User user1 = new User();
        user1.setId(1L);
        user1.setName("vyas");
        user1.setEmail("vyas@gmail.com");
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);


        when(userRepository.findByEmail("vyas@gmail.com")).thenReturn(Optional.of(list));


        Optional<List<User>> getByemail = userService.getUserByEmail("vyas@gmail.com");

        assertNotNull(getByemail);
        assertEquals(2, getByemail.get().size());
        assertEquals("vyas@gmail.com", getByemail.get().get(0).getEmail());
        assertEquals("vyas@gmail.com", getByemail.get().get(1).getEmail());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}