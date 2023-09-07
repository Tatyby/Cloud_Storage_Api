//package com.example.cloudstorageapi;
//
//import com.example.cloudstorageapi.dto.AuthRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CloudStorageApiApplicationTests {
//    String host = "http://localhost:";
//    String loginEndpoint = "/login";
//    String expected = "";
//    int port = 8070;
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//    @Container
//    private final GenericContainer<?> appContainer = new GenericContainer<>("cloud_api")
//            .withExposedPorts(port);
//
//    @Test
//    void contextLoadsLogin() {
//        AuthRequest authRequest = new AuthRequest("user", "user");
//        ResponseEntity<String> forEntity = testRestTemplate.postForEntity(host + appContainer.getMappedPort(port) +
//                                                                          loginEndpoint, authRequest, String.class);
//        System.out.println(forEntity);
//
//        Assertions.assertEquals(expected, forEntity.getBody());
//    }
//}
