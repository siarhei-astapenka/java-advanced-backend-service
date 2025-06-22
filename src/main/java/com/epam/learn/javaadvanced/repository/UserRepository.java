package com.epam.learn.javaadvanced.repository;

import com.epam.learn.javaadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
