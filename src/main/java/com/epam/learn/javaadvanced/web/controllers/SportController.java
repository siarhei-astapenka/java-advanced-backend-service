package com.epam.learn.javaadvanced.web.controllers;

import com.epam.learn.javaadvanced.service.SportService;
import com.epam.learn.javaadvanced.web.model.internal.SportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sport")
public class SportController {

    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

//    @PostMapping("/{name}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<SportResponse> create(@PathVariable String name){
//        return sportService.createSport(name);
//    }
//
//    @GetMapping
//    public Flux<SportResponse> searchUsers(@RequestParam String name) {
//        return sportService.fetchSports(name);
//    }
}
