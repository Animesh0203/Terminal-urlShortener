package com.example.URLshortner.URLshortner.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String home() {
        return "URL Shortener is Up & Running.";
    }
}
