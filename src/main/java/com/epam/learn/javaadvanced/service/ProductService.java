package com.epam.learn.javaadvanced.service;

import com.epam.learn.javaadvanced.model.ProductRequestDTO;
import com.epam.learn.javaadvanced.model.ProductResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDTO> getProducts();
    Optional<ProductResponseDTO> getProductById(Long id);
    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);
    ResponseEntity<ProductResponseDTO> putProduct(Long id, ProductRequestDTO productDTO);
    void deleteProductById(Long id);
    int getProductCount();
}
