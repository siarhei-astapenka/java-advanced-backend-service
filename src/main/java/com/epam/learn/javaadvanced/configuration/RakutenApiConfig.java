package com.epam.learn.javaadvanced.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RakutenApiConfig {

    @Value("${rakuten.api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient rakutenWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
