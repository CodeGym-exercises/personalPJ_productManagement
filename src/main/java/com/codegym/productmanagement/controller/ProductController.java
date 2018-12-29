package com.codegym.productmanagement.controller;

import com.codegym.productmanagement.model.Customer;
import com.codegym.productmanagement.model.Product;
import com.codegym.productmanagement.service.CustomerService;
import com.codegym.productmanagement.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = {"/","/products"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @GetMapping
    public String showHomePage(Model model, @PageableDefault(size = 4) Pageable pageable, HttpSession session){
        Page<Product> products = this.productService.findAll(pageable);
        model.addAttribute("products", products);
        return "home";
    }

//    ======================Create========================
    @GetMapping("/products/create")
    public String showCreatePage(Model model, HttpSession session){
        session.setAttribute("customers", this.customerService.findById(1));
        model.addAttribute("product",new Product());
        return "create";
    }

    @PostMapping("/products/create")
    public String createNewProduct(Model model, @ModelAttribute Product product, HttpSession session){
        String message = "", alertms = "";
        this.productService.save(product);
        message = "success";
        alertms = "alert alert-success";
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        //model.addAttribute("product",new Product());
        return "create";
    }

//    =====================View==============================
    @GetMapping("/products/details/{id}")
    public String showViewPage(Model model, @PathVariable int id){
        model.addAttribute("product",this.productService.findById(id));
        return "details";
    }

//    ======================Edit===============================
    @GetMapping("/products/edit/{id}")
    public String showEditPage(Model model, @PathVariable int id){
        model.addAttribute("product",this.productService.findById(id));
        return "edit";
    }

    @PostMapping("/products/edit/{id}")
    public String editProduct(Model model,@ModelAttribute("product") Product product){
        this.productService.save(product);
        String message = "", alertms = "";
        message = "success";
        alertms = "alert alert-success";
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        return "edit";
    }

//    =======================delete============================
    @GetMapping("/products/delete/{id}")
    public String showDeletePage(Model model, @PathVariable int id){
        model.addAttribute("product",this.productService.findById(id));
        return "delete";
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(Model model, @PathVariable int id){
        model.addAttribute("product",this.productService.findById(id));
        this.productService.deleteById(id);
        String message = "", alertms = "";
        message = "Delete success";
        alertms = "alert alert-success";
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        return "delete";
    }
}
