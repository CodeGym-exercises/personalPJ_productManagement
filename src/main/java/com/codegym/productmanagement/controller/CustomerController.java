package com.codegym.productmanagement.controller;

import com.codegym.productmanagement.model.Customer;
import com.codegym.productmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showListCustomerPage(Model model, @PageableDefault(size = 4)Pageable pageable){
        Page<Customer> customers = this.customerService.findAll(pageable);
        model.addAttribute("customers",customers);
        return "list_customers";
    }

    @GetMapping("/new")
    public String showCreateCustomerPage(Model model){
        model.addAttribute("customer", new Customer());
        return "cre_customer";
    }

    @PostMapping("/new")
    public String createNewCustomer(Model model, Customer customer, HttpSession session){
        session.setAttribute("current_customer",customer);

        this.customerService.save(customer);
        String message = "", alertms = "";

        message = "success";
        alertms = "alert alert-success";
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        return "cre_customer";
    }
}
