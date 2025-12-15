package com.cart.cart.services;

import com.cart.cart.model.Product;
import com.cart.cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"D\",\"location\":\"ProductService.java:22\",\"message\":\"getProductById entry\",\"data\":{\"id\":"+(id!=null?id:"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion
        if (id == null) {
            return Optional.empty();
        }
        return productRepo.findById(id);
    }

    public void saveProduct(Product product) {
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"D\",\"location\":\"ProductService.java:26\",\"message\":\"saveProduct entry\",\"data\":{\"product\":"+(product!=null?"not null":"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"D\",\"location\":\"ProductService.java:30\",\"message\":\"deleteProduct entry\",\"data\":{\"id\":"+(id!=null?id:"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion
        if (id == null) {
            return;
        }
        productRepo.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterByPrice(double min, double max) {
        return productRepo.findByPriceBetween(min, max);
    }
}