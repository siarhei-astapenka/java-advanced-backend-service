package com.epam.learn.javaadvanced.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AboutService {

    @Value("${spring.application.name}")
    private String appName;

    public String getAbout() {
        return "This is a public about page. No authentication required. Application name: " + appName;
    }
}
