package com.example.cloudstorageapi.repository;

import com.example.cloudstorageapi.entity.FileCloud;
import com.example.cloudstorageapi.entity.UserCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<FileCloud, Integer> {

    Optional<FileCloud> findByName(String name);

    void removeFileByName(String fileName);

    List<FileCloud> findAllFilesByUser(UserCloud user);

}
