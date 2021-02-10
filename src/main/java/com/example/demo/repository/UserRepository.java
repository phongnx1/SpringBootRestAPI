package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.status = 1")
    List<User> findAllActiveUsers();

    //Using pagination in the JPQL query definition is straightforward:
    @Query(value = "SELECT u FROM User u ORDER BY id")
    List<User> findAllUsers(Pageable pageable);

    // // https://www.baeldung.com/spring-data-jpa-query
    // @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
    // User findUserByStatusAndName(Integer status, String name);
}