package com.project.demo.repository;

import com.project.demo.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    boolean existsByEmail(String email);

    List<User> findAll();
}
