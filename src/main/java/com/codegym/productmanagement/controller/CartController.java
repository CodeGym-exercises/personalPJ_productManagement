package com.codegym.productmanagement.controller;

import com.codegym.productmanagement.model.Item;
import com.codegym.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mycart")
public class CartController {
    @Autowired
    ProductService productService;

    static List<Item> cart = new ArrayList<Item>();

    @GetMapping
    public String showCartPage(Model model){
        return "mycart";
    }

    @GetMapping("/add/{id}")
    public String showCartPage(Model model, @PathVariable int id, HttpSession session){
        if(session.getAttribute("items") == null){
            cart.add(new Item(this.productService.findById(id),1));
        }
        else{
            cart.add(new Item(this.productService.findById(id),1));
        }
        session.setAttribute("items",cart);
        session.setAttribute("total",getSum());
        return "mycart";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(Model model, @PathVariable int id, HttpSession session){
        cart.remove(findByProductId(id));
        session.setAttribute("items",cart);
        session.setAttribute("total",getSum());
        return "mycart";
    }

    Item findByProductId(int id){
        for( int i = 0; i < cart.size(); i++){
            if(cart.get(i).getProduct().getId()==id){
                return cart.get(i);
            }
        }
        return cart.get(0);
    }

    int getSum(){
        int sum = 0;
        for( int i = 0; i < cart.size(); i++){
          sum+= Integer.parseInt(cart.get(i).getProduct().getPrice());
        }
        return sum;
    }
}


