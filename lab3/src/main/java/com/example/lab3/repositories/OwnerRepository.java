package com.example.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab3.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
