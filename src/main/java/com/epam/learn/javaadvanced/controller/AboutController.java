package com.epam.learn.javaadvanced.controller;

import com.epam.learn.javaadvanced.service.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("/about")
    public String about() {
        return aboutService.getAbout();
    }
}
