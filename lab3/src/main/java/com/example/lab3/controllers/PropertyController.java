package com.example.lab3.controllers;

import com.example.lab3.dto.PropertyDTO;
import com.example.lab3.models.Property;
import com.example.lab3.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
  private final PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping
  public ResponseEntity<Property> createProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
    Property property = propertyService.createProperty(propertyDTO);
    if (property != null) {
      return new ResponseEntity<>(property, HttpStatus.CREATED);
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  public ResponseEntity<List<Property>> getAllProperties() {
    List<Property> properties = propertyService.getAllProperties();
    return ResponseEntity.ok(properties);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
    Property property = propertyService.getPropertyById(id);
    if (property != null) {
      return ResponseEntity.ok(property);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/owner/{ownerId}")
  public ResponseEntity<List<Property>> getPropertiesByOwner(@PathVariable Long ownerId) {
    List<Property> properties = propertyService.getPropertiesByOwner(ownerId);
    return ResponseEntity.ok(properties);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Property> updateProperty(
      @PathVariable Long id,
      @Valid @RequestBody PropertyDTO propertyDTO) {
    Property property = propertyService.updateProperty(id, propertyDTO);
    if (property != null) {
      return ResponseEntity.ok(property);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
    propertyService.deleteProperty(id);
    return ResponseEntity.noContent().build();
  }
}
