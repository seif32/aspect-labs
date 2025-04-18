package com.example.lab3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OwnerDTO {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Phone is required")
  @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
  private String phone;
}
