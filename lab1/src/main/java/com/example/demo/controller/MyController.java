package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class MyController {

  @GetMapping
  public ResponseEntity<String> getResource() {
    return ResponseEntity.ok("GET Request: Fetching resource");
  }

  @PostMapping
  public ResponseEntity<String> createResource() {
    return ResponseEntity.ok("POST Request: Creating resource");
  }
}
