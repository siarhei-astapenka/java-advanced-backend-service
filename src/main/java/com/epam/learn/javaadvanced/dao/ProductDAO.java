package com.epam.learn.javaadvanced.dao;

import com.epam.learn.javaadvanced.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    List<Product> getProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProductById(Long id);
    boolean isProductExist(Long id);
}
