package com.example.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab3.models.Property;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
  List<Property> findByOwnerId(Long ownerId);
}
