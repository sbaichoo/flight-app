package com.maureva.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<String> demoController() {
        return ResponseEntity.ok("Hi there!");
    }
}