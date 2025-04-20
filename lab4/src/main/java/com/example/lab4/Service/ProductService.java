package com.example.lab4.Service;

import com.example.lab4.Annotation.DistributedLock;
import com.example.lab4.Model.Product;
import com.example.lab4.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Cacheable(value = "products", key = "#id")
  public Product getProductById(Long id) {
    System.out.println("Fetching from database...");
    return productRepository.findById(id).orElse(null);
  }

  @CachePut(value = "products", key = "#product.id")
  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  @CacheEvict(value = "products", key = "#id")
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @DistributedLock
  public String longOperation() throws InterruptedException {
    // This simulates a long operation
    Thread.sleep(10000); // 10 seconds
    return "Operation completed after delay";
  }
}
