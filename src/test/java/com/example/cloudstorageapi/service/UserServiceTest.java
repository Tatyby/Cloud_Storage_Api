package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.entity.UserCloud;
import com.example.cloudstorageapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddTokenToUser() {
        UserService userService = new UserService(userRepository);
        userService.addTokenToUser("username", "token");
        verify(userRepository).addTokenToUser("username", "token");
    }

    @Test
    public void testGetUserByLogin() {
        UserCloud userCloud = new UserCloud(1, "username", "token", "password");
        when(userRepository.findByLogin("username")).thenReturn(userCloud);

        UserService userService = new UserService(userRepository);

        UserDetails userDetails = userService.getUserByLogin("username");

        verify(userRepository).findByLogin("username");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("username", userDetails.getUsername());
        Assertions.assertEquals("password", userDetails.getPassword());


    }
}
