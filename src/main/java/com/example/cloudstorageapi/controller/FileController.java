package com.example.cloudstorageapi.controller;

import com.example.cloudstorageapi.dto.FileResponse;
import com.example.cloudstorageapi.entity.FileCloud;
import com.example.cloudstorageapi.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class FileController {
    @Autowired
    private final FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("filename") String filename,
                                        @RequestBody MultipartFile file) {
        fileService.upload(filename, file);
        log.info("загружен файл " + filename);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> delete(@RequestParam("filename") String fileName) {
        fileService.delete(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam("filename") String filename) {
        FileCloud file = fileService.getFile(filename);
        log.info("работа метода getfile");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileResponse>> showSavedFiles(@RequestParam("limit") int limit) {
        log.info("работа метода showSavedFiles");
        return new ResponseEntity<>(fileService.show(limit), HttpStatus.OK);

    }

    @PutMapping("/file")
    public ResponseEntity<?> updateFileName(@RequestParam("filename") String fileName,
                                            @RequestBody String newFileName) {
        return new ResponseEntity<>(fileService.updateFileName(fileName, newFileName), HttpStatus.OK);
    }

}
