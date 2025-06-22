package com.epam.learn.javaadvanced.controller;

import com.epam.learn.javaadvanced.model.InfoResponse;
import com.epam.learn.javaadvanced.service.InfoServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    private final InfoServices infoServices;

    public InfoController(InfoServices infoServices) {
        this.infoServices = infoServices;
    }

    @GetMapping("/info")
    public InfoResponse getRandomStat() {
        return infoServices.getRandomStats();
    }
}
