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
        cartItems.removeIf(p -> {
            return p.getId() != null && p.getId().equals(productId);
        });
    }

    public void clear() {
        cartItems.clear();
    }

    public double getTotal() {
        return cartItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}