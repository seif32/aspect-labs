package com.example.lab2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data // Lombok annotation for getters, setters, and toString
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String username;
  private String password;
  private String phoneNumber;
}
