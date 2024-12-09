package com.sg.iss.nus.Self_revision_task_2.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sg.iss.nus.Self_revision_task_2.model.Product;
import com.sg.iss.nus.Self_revision_task_2.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public String showProductPage(Model model) {
        Map<String, Product> productList = productService.getProductList();
        model.addAttribute("products", productList);

        return "productList";
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestParam String title, Model model) {
        boolean isSuccessful = productService.buyProduct(title);

        if (!isSuccessful) {
            model.addAttribute("productTitle", title);
            return "productUnavailable";
        }

        return "redirect:/products/details?title=" + title;
    }

    @GetMapping("/details")
    public String showProductDetails(@RequestParam String title, Model model){

        Product product = productService.getProductDetails(title);
        model.addAttribute("product", product);
        return "productDetails";
    }
}
