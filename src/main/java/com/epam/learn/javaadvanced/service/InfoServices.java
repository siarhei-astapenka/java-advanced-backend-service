package com.epam.learn.javaadvanced.service;

import com.epam.learn.javaadvanced.model.InfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InfoServices {

    private final Random random = new Random();

    @Value("${spring.application.name}")
    private String appName;

    public InfoResponse getRandomStats() {
        return InfoResponse.builder()
                .appName(appName)
                .activeUsers(random.nextInt(100))
                .serverLoad(random.nextInt(100))
                .requestsProcessed(random.nextInt(100))
                .memoryUsage(random.nextInt(100))
                .cpuUsage(random.nextInt(100))
                .build();
    }
}
