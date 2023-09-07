package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Test
    public void testLogoutUser() {
        String token = "someToken";
        AuthService userService = new AuthService(userRepository);
        userService.logoutUser(token);
        Mockito.verify(userRepository).removeToken(token);
    }
}
