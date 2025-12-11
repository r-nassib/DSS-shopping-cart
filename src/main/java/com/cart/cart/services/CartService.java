package com.cart.cart.services;

import com.cart.cart.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final List<Product> cartItems = new ArrayList<>();

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public void removeProduct(Long productId) {
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"C\",\"location\":\"CartService.java:22\",\"message\":\"removeProduct entry\",\"data\":{\"productId\":"+(productId!=null?productId:"null")+",\"cartSize\":"+cartItems.size()+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion
        cartItems.removeIf(p -> {
            // #region agent log
            try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"C\",\"location\":\"CartService.java:23\",\"message\":\"checking product id\",\"data\":{\"productId\":"+(p.getId()!=null?p.getId():"null")+",\"targetId\":"+(productId!=null?productId:"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
            // #endregion
            return p.getId() != null && p.getId().equals(productId);
        });
    }

    public void clear() {
        cartItems.clear();
    }
}