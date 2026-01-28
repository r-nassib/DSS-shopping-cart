package com.cart.cart.controller;

import com.cart.cart.model.Product;
import com.cart.cart.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "productos";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {
        List<Product> products;
        boolean hasName = name != null && !name.isEmpty();
        boolean hasPrice = minPrice != null && maxPrice != null;

        if (hasName && hasPrice) {
            double min = minPrice != null ? minPrice : 0.0;
            double max = maxPrice != null ? maxPrice : Double.MAX_VALUE;
            products = productService.searchByName(name).stream()
                    .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                    .toList();
        } else if (hasName) {
            products = productService.searchByName(name);
        } else if (hasPrice) {
            double min = minPrice != null ? minPrice : 0.0;
            double max = maxPrice != null ? maxPrice : Double.MAX_VALUE;
            products = productService.filterByPrice(min, max);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        return "productos";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "formulario-producto";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        System.out.println("Id del producto --> " + id);
        Product product = productService.getProductById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        System.out.println("ID: " + product.getId());
        model.addAttribute("product", product);
        return "formulario-producto";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}