package com.codegym.productmanagement.service;

import com.codegym.productmanagement.model.Customer;

import java.util.Iterator;
import java.util.List;

public interface CustomerService {
    Iterator<Customer> findAll();

    Customer findById(int id);

    void deleteById(int id);

    void save(Customer customer);
}
