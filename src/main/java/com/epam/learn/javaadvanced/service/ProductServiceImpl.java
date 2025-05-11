package com.epam.learn.javaadvanced.service;

import com.epam.learn.javaadvanced.dao.ProductDAO;
import com.epam.learn.javaadvanced.entity.Product;
import com.epam.learn.javaadvanced.model.ProductRequestDTO;
import com.epam.learn.javaadvanced.model.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductDAO productDAO, ModelMapper modelMapper) {
        this.productDAO = productDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        return Optional.ofNullable(productDAO.getProducts())
            .orElseGet(Collections::emptyList)
            .stream()
            .map(product -> modelMapper.map(product, ProductResponseDTO.class))
            .toList();
    }

    @Override
    public Optional<ProductResponseDTO> getProductById(Long id) {
        return productDAO.getProductById(id)
            .map(value -> modelMapper.map(value, ProductResponseDTO.class));
    }

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        Product savedProduct = productDAO.saveProduct(product);
        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> putProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        boolean isProductExist = isProductExist(id);

        if (isProductExist) {
            product.setId(id);
        }

        Product updatedProduct = productDAO.saveProduct(product);
        ProductResponseDTO productResponseDTO = modelMapper.map(updatedProduct, ProductResponseDTO.class);

        return isProductExist
                ? new ResponseEntity<>(productResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

    @Override
    public void deleteProductById(Long id) {
        productDAO.deleteProductById(id);
    }

    private boolean isProductExist(Long id) {
        return productDAO.isProductExist(id);
    }
}
