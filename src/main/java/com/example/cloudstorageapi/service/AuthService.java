package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
@AllArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;

    public void logoutUser(String token) {
        userRepository.removeToken(token);
    }

}
