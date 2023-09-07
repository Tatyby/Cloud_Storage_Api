package com.example.cloudstorageapi.service;

import com.example.cloudstorageapi.dto.FileResponse;
import com.example.cloudstorageapi.entity.FileCloud;
import com.example.cloudstorageapi.exception.IncorrectDataException;
import com.example.cloudstorageapi.repository.FilesRepository;
import com.example.cloudstorageapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class FileService {
    @Autowired
    private final FilesRepository filesRepository;
    @Autowired
    private final UserRepository userRepository;

    @SneakyThrows
    public void upload(String fileName, MultipartFile files) {
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        var file = FileCloud.builder()
                .name(fileName)
                .data(files.getBytes())
                .size(files.getSize())
                .user(userRepository.findByLogin(login))
                .createDate(LocalDate.now()).build();

        filesRepository.save(file);
    }

    public void delete(String fileName) {
        filesRepository.removeFileByName(fileName);
        log.info("Файл " + fileName + " удален");
    }

    public List<FileResponse> show(int limit) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("логин " + login);
        var fileList = filesRepository.findAllFilesByUser(userRepository.findByLogin(login));
        var user = userRepository.findByLogin(login);
        log.info("юзер по логину " + login + ": " + user);
        List<FileResponse> list = new ArrayList<>();
        for (var file : fileList) {
            var fileResponse = new FileResponse(file.getName(), file.getSize());
            if (limit-- == 0) break;
            list.add(fileResponse);
            log.info("отобразили файлы " + file.getName());
        }
        return list;
    }

    public FileCloud getFile(String fileName) {
        Optional<FileCloud> optionalFile = filesRepository.findByName(fileName);
        if (optionalFile.isEmpty()) {
            log.info("Файла с таким именем не существует: " + fileName);
            throw new IncorrectDataException("Файла с таким именем не существует " + fileName);
        }
        log.info("скачали файл " + optionalFile.get().getName());
        return optionalFile.get();
    }

    public FileCloud updateFileName(String fileName, String newName) {
        Optional<FileCloud> fileCloudOptional = filesRepository.findByName(fileName);
        if (fileCloudOptional.isEmpty()) {
            log.info("Файла с таким именем не существует: " + fileName);
            throw new IncorrectDataException("Файла с таким именем не существует");
        }
        FileCloud file = fileCloudOptional.get();
        log.info("получен файл: " + file.getName());
        log.info(newName);
        String newFileName = newName.substring((newName.indexOf(":\"") + 2), newName.lastIndexOf("\""));
        file.setName(newFileName);
        log.info("файл переименован в " + newFileName);
        filesRepository.save(file);
        log.info("файл " + newFileName + " сохранен в БД");
        return file;
    }


}
