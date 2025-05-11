package com.epam.learn.javaadvanced.monitor;

import com.epam.learn.javaadvanced.service.ProductService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceHealthIndicator implements HealthIndicator {

    private final ProductService productService;

    public ProductServiceHealthIndicator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Health health() {
        try {
            int productCount = productService.getProductCount();
            return Health.up()
                .withDetail("productCount", productCount)
                .withDetail("message", "Service is running with " + productCount + " products")
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("error", e.getMessage())
                .build();
        }
    }
}
