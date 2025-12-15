package com.cart.cart.controller;

import com.cart.cart.model.Product;
import com.cart.cart.services.CartService;
import com.cart.cart.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id).orElse(null);
        System.out.println("Agregando al carrito");
        System.out.println("Id del producto --> " + id);

        if (product != null) {
            cartService.addProduct(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeProduct(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}