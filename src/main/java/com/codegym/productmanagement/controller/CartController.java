package com.codegym.productmanagement.controller;

import com.codegym.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mycart")
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping
    public String showCartPage(Model model){
        return "mycart";
    }

    @GetMapping("/add/{id}")
    public String showCartPage(Model model, @PathVariable int id, HttpSession session){

        return "mycart";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(Model model, @PathVariable int id, HttpSession session){

        return "mycart";
    }

}
