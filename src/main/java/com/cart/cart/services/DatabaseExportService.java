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
                // #region agent log
                try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"B\",\"location\":\"DatabaseExportService.java:27\",\"message\":\"processing product\",\"data\":{\"productId\":"+(product.getId()!=null?product.getId():"null")+",\"productName\":"+(product.getName()!=null?"\""+product.getName()+"\"":"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
                // #endregion
                sb.append("INSERT INTO product (id, name, price) VALUES (")
                        .append(product.getId()).append(", ")
                        .append("'").append(product.getName() != null ? product.getName().replace("'", "''") : "").append("', ")
                        .append(product.getPrice())
                        .append(");\n");
            }
        }

        return sb.toString();
    }
}