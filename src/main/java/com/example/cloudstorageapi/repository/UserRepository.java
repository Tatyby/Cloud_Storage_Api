package com.example.cloudstorageapi.repository;


import com.example.cloudstorageapi.entity.UserCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.constraints.NotNull;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserCloud,Integer> {
    @NotNull UserCloud save(@NotNull UserCloud userCloud);
    @Query("update UserCloud  set token=null where token = :token")
    @Modifying
    void removeToken(String token);
    UserCloud findByLogin(String login);
    @Modifying
    @Query("update UserCloud u set u.token = :token where u.login = :login")
    void addTokenToUser(String login, String token);
}
