package com.epam.learn.javaadvanced.repository;

import com.epam.learn.javaadvanced.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2ProductRepository extends JpaRepository<Product, Long> {}
