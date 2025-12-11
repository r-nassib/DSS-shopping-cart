package com.cart.cart.controller;

import com.cart.cart.model.Product;
import com.cart.cart.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"A\",\"location\":\"ProductController.java:27\",\"message\":\"searchProducts entry\",\"data\":{\"name\":\""+(name!=null?name:"null")+"\",\"minPrice\":"+(minPrice!=null?minPrice:"null")+",\"maxPrice\":"+(maxPrice!=null?maxPrice:"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion
        List<Product> products;
        boolean hasName = name != null && !name.isEmpty();
        boolean hasPrice = minPrice != null && maxPrice != null;
        // #region agent log
        try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"A\",\"location\":\"ProductController.java:34\",\"message\":\"hasPrice check\",\"data\":{\"hasPrice\":"+hasPrice+",\"minPrice\":"+(minPrice!=null?minPrice:"null")+",\"maxPrice\":"+(maxPrice!=null?maxPrice:"null")+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
        // #endregion

        if (hasName && hasPrice) {
            // #region agent log
            try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"A\",\"location\":\"ProductController.java:36\",\"message\":\"filtering with prices\",\"data\":{\"minPrice\":"+minPrice+",\"maxPrice\":"+maxPrice+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
            // #endregion
            double min = minPrice != null ? minPrice : 0.0;
            double max = maxPrice != null ? maxPrice : Double.MAX_VALUE;
            products = productService.searchByName(name).stream()
                    .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                    .toList();
        } else if (hasName) {
            products = productService.searchByName(name);
        } else if (hasPrice) {
            // #region agent log
            try { java.io.FileWriter fw = new java.io.FileWriter("/Users/raulhidalgo/Documents/MASTER-INGENIERIA-INFORMATICA/DSS/DSS-shopping-cart/.cursor/debug.log", true); fw.write("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"A\",\"location\":\"ProductController.java:42\",\"message\":\"filterByPrice call\",\"data\":{\"minPrice\":"+minPrice+",\"maxPrice\":"+maxPrice+"},\"timestamp\":"+System.currentTimeMillis()+"}\n"); fw.close(); } catch (Exception e) {}
            // #endregion
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
        Product product = productService.getProductById(id).orElse(new Product());
        System.out.println("ID: " + product.getId());
        model.addAttribute("product", product);
        return "formulario-producto";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}