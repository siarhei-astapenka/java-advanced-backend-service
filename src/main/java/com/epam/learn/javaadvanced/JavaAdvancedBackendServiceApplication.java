package com.epam.learn.javaadvanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAdvancedBackendServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaAdvancedBackendServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello World!");
    }
}
