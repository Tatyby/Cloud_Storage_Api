package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.entity.FileCloud;
import com.example.cloudstorageapi.entity.UserCloud;
import com.example.cloudstorageapi.repository.FilesRepository;
import com.example.cloudstorageapi.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class FileServiceTest {
    @Mock
    private FilesRepository filesRepository;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void setup() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @SneakyThrows
    @Test
    public void uploadTest() {

        MockMultipartFile mockFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Test file".getBytes());
        String login = "username";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        when(authentication.getName()).thenReturn(login);

        UserCloud userCloud = new UserCloud(1, "username", "token", "password");
        FileService fileService = new FileService(filesRepository, userRepository);
        when(userRepository.findByLogin(login)).thenReturn(userCloud);
        FileCloud fileCloud = FileCloud.builder()
                .name("test.txt")
                .data(mockFile.getBytes())
                .size(mockFile.getSize())
                .user(userCloud)
                .createDate(LocalDate.now())
                .build();
        fileService.upload("test.txt", mockFile);
        verify(userRepository).findByLogin(login);
        verify(filesRepository).save(fileCloud);
    }

    @Test
    public void deleteTest() {
        String fileName = "test.txt";
        FileService fileService = new FileService(filesRepository, userRepository);
        fileService.delete(fileName);
        verify(filesRepository).removeFileByName(fileName);
    }

    @Test
    public void getFileTest() {
        String fileName = "example.txt";
        FileCloud file = new FileCloud(1, "file1", 1L, null, null, new UserCloud(1, "user", "login", "user"));
        when(filesRepository.findByName(fileName)).thenReturn(Optional.of(file));
        FileService fileService = new FileService(filesRepository, userRepository);
        FileCloud result = fileService.getFile(fileName);
        Assertions.assertEquals(file, result);
    }

    @Test
    public void updateFileNameTest() {
        String fileName = "example.txt";
        String newName = "{\"filename\":\"586.xlsx\"}";
        String expected = "586.xlsx";
        FileCloud file = new FileCloud(1, "example.txt", 1L, null, null, new UserCloud(1, "user", "login", "user"));
        when(filesRepository.findByName(fileName)).thenReturn(Optional.of(file));
        FileService fileService = new FileService(filesRepository, userRepository);
        FileCloud result = fileService.updateFileName(fileName, newName);
        Assertions.assertEquals(file, result);
        Assertions.assertEquals(expected, file.getName());
        verify(filesRepository).save(file);
    }
}
