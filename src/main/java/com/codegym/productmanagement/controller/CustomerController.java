package com.codegym.productmanagement.controller;

import com.codegym.productmanagement.model.Customer;
import com.codegym.productmanagement.model.Product;
import com.codegym.productmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        List<Product> cart = new ArrayList<>();
        session.setAttribute("cart",cart);
        session.setAttribute("current_customer",customer);

        this.customerService.save(customer);
        String message = "", alertms = "";

        message = "success";
        alertms = "alert alert-success";
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        return "cre_customer";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session, String email, String name){
        String message = "",alertms = "";
        if(isCustomer(email, name)!=-1){
            message = "success";
            alertms = "alert alert-success";
            session.setAttribute("current_customer",this.customerService.findById(isCustomer(email, name)));
            return "redirect:/products";
        }else{
            message = "Fails, not found this customer in system";
            alertms = "alert alert-danger";
        }
        model.addAttribute("message",message);
        model.addAttribute("alertms",alertms);
        return "login";
    }

    private int isCustomer(String email, String name){
        ArrayList<Customer> listCustomer = this.customerService.findAll();
        for(Customer customer : listCustomer){
            if(customer.getEmail().equals(email)&&
                    customer.getName().equals(name)){
                return customer.getId();
            }
        }
        return -1;
    }


}
