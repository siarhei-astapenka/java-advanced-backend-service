package com.epam.learn.javaadvanced.dao;

import com.epam.learn.javaadvanced.entity.Product;
import com.epam.learn.javaadvanced.repository.H2ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class H2ProductDAO implements ProductDAO {
    private final H2ProductRepository productRepository;

    public H2ProductDAO(H2ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isProductExist(Long id) {
        return productRepository.existsById(id);
    }
}
