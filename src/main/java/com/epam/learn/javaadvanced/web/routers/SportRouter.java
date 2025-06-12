package com.epam.learn.javaadvanced.web.routers;

import com.epam.learn.javaadvanced.web.handlers.SportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class SportRouter {
    @Bean
    public RouterFunction<ServerResponse> sportRoutes(SportHandler sportHandler) {
        return RouterFunctions.route()
                .path("/api/v1/sport", builder -> builder
                        .POST("/{sportname}", accept(MediaType.APPLICATION_JSON), sportHandler::createSport)
                        .GET("", accept(MediaType.APPLICATION_JSON), sportHandler::searchSports)
                )
                .build();
    }
}
