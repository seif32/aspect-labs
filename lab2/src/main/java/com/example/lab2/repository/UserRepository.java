package com.example.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab2.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
