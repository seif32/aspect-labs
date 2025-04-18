package com.example.lab3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PropertyDTO {
  @NotBlank(message = "Address is required")
  private String address;

  @NotBlank(message = "City is required")
  private String city;

  @NotBlank(message = "State is required")
  private String state;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotNull(message = "Owner ID is required")
  private Long ownerId;
}
