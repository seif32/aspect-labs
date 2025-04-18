package com.example.lab3.controllers;

import com.example.lab3.dto.OwnerDTO;
import com.example.lab3.models.Owner;
import com.example.lab3.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @PostMapping
  public ResponseEntity<Owner> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
    Owner owner = ownerService.createOwner(ownerDTO);
    return new ResponseEntity<>(owner, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Owner>> getAllOwners() {
    List<Owner> owners = ownerService.getAllOwners();
    return ResponseEntity.ok(owners);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
    Owner owner = ownerService.getOwnerById(id);
    if (owner != null) {
      return ResponseEntity.ok(owner);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Owner> updateOwner(
      @PathVariable Long id,
      @Valid @RequestBody OwnerDTO ownerDTO) {
    Owner owner = ownerService.updateOwner(id, ownerDTO);
    if (owner != null) {
      return ResponseEntity.ok(owner);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
    ownerService.deleteOwner(id);
    return ResponseEntity.noContent().build();
  }
}
