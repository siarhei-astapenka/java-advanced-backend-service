package com.epam.learn.javaadvanced.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class ProductMetricsService {
    private final Counter productCreationCounter;
    private final Counter productUpdateCounter;
    private final Counter productDeletionCounter;

    public ProductMetricsService(MeterRegistry registry) {
        productCreationCounter = Counter.builder("product.operations")
            .tag("operation", "create")
            .description("Total number of product creations")
            .register(registry);

        productUpdateCounter = Counter.builder("product.operations")
            .tag("operation", "update")
            .description("Total number of product updates")
            .register(registry);

        productDeletionCounter = Counter.builder("product.operations")
            .tag("operation", "delete")
            .description("Total number of product deletions")
            .register(registry);
    }

    public void incrementCreationCount() {
        productCreationCounter.increment();
    }

    public void incrementUpdateCount() {
        productUpdateCounter.increment();
    }

    public void incrementDeletionCount() {
        productDeletionCounter.increment();
    }

}
