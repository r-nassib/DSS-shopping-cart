package com.cart.cart.services;

import com.cart.cart.model.CartItem;
import com.cart.cart.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CartService {

    private final Map<Long, CartItem> cartItems = new HashMap<>();

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems.values());
    }

    public void addProduct(Product product) {
        if (cartItems.containsKey(product.getId())) {
            CartItem item = cartItems.get(product.getId());
            item.setQuantity(item.getQuantity() + 1);
        } else {
            cartItems.put(product.getId(), new CartItem(product, 1));
        }
    }

    public void removeProduct(Long productId) {
        cartItems.remove(productId);
    }

    public void clear() {
        cartItems.clear();
    }

    public double getTotal() {
        return cartItems.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}