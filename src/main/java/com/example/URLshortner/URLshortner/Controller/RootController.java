package com.example.URLshortner.URLshortner.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.URLshortner.URLshortner.Service.urlService;

import jakarta.servlet.http.HttpServletResponse;

import com.example.URLshortner.URLshortner.Model.urlModel;
import com.example.URLshortner.URLshortner.Repository.urlRepo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/url")
public class RootController {

    @Autowired
    private final urlService urlService;
    private final urlRepo urlRepository;

    public RootController(urlService urlService, urlRepo urlRepository) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the URL Shortener API";
    }

    @GetMapping("/status")
    public String status() {
        return "URL Shortener API is running";
    }

    @PostMapping("/shorten")
    public String shortenURL(@RequestParam String originalURL) {
        return urlService.generateShortURL(originalURL);
    }

    @GetMapping("/expand")
    public String expandURL(@RequestParam String shortURL) {
        return urlService.returnOriginalURL(shortURL);
    }

    @GetMapping("/{code}")
    public void redirectToLongUrl(@PathVariable String code, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.returnOriginalURL(code);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short URL not found!");
        }
    }

}
