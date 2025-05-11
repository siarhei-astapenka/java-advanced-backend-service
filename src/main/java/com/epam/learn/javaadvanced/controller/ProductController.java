package com.epam.learn.javaadvanced.controller;

import com.epam.learn.javaadvanced.model.ProductRequestDTO;
import com.epam.learn.javaadvanced.model.ProductResponseDTO;
import com.epam.learn.javaadvanced.service.ProductService;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Timed(value = "products.get.all", description = "Time taken to get all products")
    List<ProductResponseDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Timed(value = "products.get.by.id", description = "Time taken to get product by ID")
    Optional<ProductResponseDTO> getProductById(@PathVariable @NotNull @Min(1) Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Timed(value = "products.post", description = "Time taken to post a new product")
    ProductResponseDTO saveProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed(value = "products.put.by.id", description = "Time taken to update existing/create new product by ID")
    ResponseEntity<ProductResponseDTO> putProduct(@PathVariable @NotNull @Min(1) Long id,
                                       @Valid @RequestBody ProductRequestDTO productRequest) {
        return productService.putProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed(value = "products.delete.by.id", description = "Time taken to delete product by ID")
    void deleteProduct(@PathVariable @NotNull @Min(1) Long id) {
        productService.deleteProductById(id);
    }
}
