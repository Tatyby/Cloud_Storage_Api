package com.example.cloudstorageapi;


import com.example.cloudstorageapi.entity.UserCloud;
import com.example.cloudstorageapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudStorageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStorageApiApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository users) {
      return args -> users.save(new UserCloud(1,"user",null, "user"));

    }
}

