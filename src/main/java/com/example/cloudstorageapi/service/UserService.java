package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.repository.UserRepository;
import com.example.cloudstorageapi.entity.UserCloud;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserDetails getUserByLogin(String login) {

        UserCloud userCloud = userRepository.findByLogin(login);
        return userCloud == null ? null : new User(userCloud.getLogin(), userCloud.getPassword(), new ArrayList<>());
    }

    public void addTokenToUser(String login, String token) {
        userRepository.addTokenToUser(login, token);
    }

}
