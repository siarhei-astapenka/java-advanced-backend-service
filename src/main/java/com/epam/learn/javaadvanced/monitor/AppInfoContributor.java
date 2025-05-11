package com.epam.learn.javaadvanced.monitor;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("application",
            Map.of(
            "name", "Product Service",
            "description", "Spring Boot REST API for product management",
            "version", "0.0.1-SNAPSHOT"
            ));
    }
}
