package com.example.demo.controller.v1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    @GetMapping("/api/v1/test")
    public String getStatusMessage(@RequestParam String message) {
        return message;
    }
    
}
