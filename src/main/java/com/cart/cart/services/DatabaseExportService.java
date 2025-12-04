package com.cart.cart.services;

import com.cart.cart.model.Product;
import com.cart.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseExportService {

    @Autowired
    private ProductRepository productRepository;

    public String exportDatabaseToSql() {
        List<Product> products = productRepository.findAll();
        StringBuilder sb = new StringBuilder();
        
        // Add header comment
        sb.append("-- Product Database Export\n");
        sb.append("-- Generated SQL script\n\n");

        if (products.isEmpty()) {
            sb.append("-- No products found\n");
        } else {
            for (Product product : products) {
                sb.append("INSERT INTO product (id, name, price) VALUES (")
                        .append(product.getId()).append(", ")
                        .append("'").append(product.getName().replace("'", "''")).append("', ")
                        .append(product.getPrice())
                        .append(");\n");
            }
        }

        return sb.toString();
    }
}