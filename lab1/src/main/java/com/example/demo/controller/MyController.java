package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping
  public ResponseEntity<String> updateResource() {
    return ResponseEntity.ok("PUT Request: Updating resource");
  }

  @DeleteMapping
  public ResponseEntity<String> deleteResource() {
    return ResponseEntity.ok("DELETE Request: Deleting resource");
  }
}
