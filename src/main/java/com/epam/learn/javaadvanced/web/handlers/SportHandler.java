package com.epam.learn.javaadvanced.web.handlers;

import com.epam.learn.javaadvanced.service.SportService;
import com.epam.learn.javaadvanced.web.errors.GlobalExceptionHandler;
import com.epam.learn.javaadvanced.web.model.internal.SportResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SportHandler {
    private final SportService sportService;

    public SportHandler(SportService sportService) {
        this.sportService = sportService;
    }

    public Mono<ServerResponse> createSport(ServerRequest request) {
        String sportName = request.pathVariable("sportname");

        return sportService.createSport(sportName)
                .flatMap(sport -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sport))
                .onErrorResume(DuplicateKeyException.class, e ->
                        ServerResponse.status(HttpStatus.CONFLICT)
                                .bodyValue(new GlobalExceptionHandler.ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage())));
    }

    public Mono<ServerResponse> searchSports(ServerRequest request) {
        String query = request.queryParam("q").orElse("");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sportService.searchSports(query), SportResponse.class);
    }
}
