package com.example.lab4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab4.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
