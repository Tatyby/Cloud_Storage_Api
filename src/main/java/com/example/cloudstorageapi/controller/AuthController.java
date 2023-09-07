package com.example.cloudstorageapi.controller;


import com.example.cloudstorageapi.dto.AuthRequest;
import com.example.cloudstorageapi.dto.AuthResponse;
import com.example.cloudstorageapi.security.JwtTokenUtil;
import com.example.cloudstorageapi.service.AuthService;
import com.example.cloudstorageapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@Log4j2
public class AuthController {
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        final UserDetails userDetails = userService.getUserByLogin(authRequest.getLogin());
        log.info("получили UserDetails " + userDetails);
        log.info("работа метода createAuthenticationToken");
        if (userDetails != null) {
            var name = userDetails.getUsername();
            var pass = userDetails.getPassword();
            if (name.equals(authRequest.getLogin()) && pass.equals(authRequest.getPassword())) {
                final String token = jwtTokenUtil.generateToken(userDetails);
                userService.addTokenToUser(authRequest.getLogin(), token);
                log.info("залогинилсь под " + name);
                return ResponseEntity.status(HttpStatus.OK).body(AuthResponse.builder().authToken(token).build());
            }
        }
        return ResponseEntity.status(400).body("Bad credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authService.logoutUser(authToken);
        log.info("разлогитнились");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

