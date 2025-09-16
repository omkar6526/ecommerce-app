package com.omkar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

	@GetMapping
    public String welcome() {
        return "Welcome to Omkar's E-Commerce API!";
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "API is up and running.";
    }
}
