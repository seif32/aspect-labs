package com.example.lab4.Controller;

import com.example.lab4.Annotation.RateLimited;
import com.example.lab4.Model.Product;
import com.example.lab4.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/{id}")
  @RateLimited
  public Product getProduct(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/long-operation")
  public String longOperation() throws InterruptedException {
    return productService.longOperation();
  }
}
