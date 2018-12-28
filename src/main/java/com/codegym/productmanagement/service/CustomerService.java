package com.codegym.productmanagement.service;

import com.codegym.productmanagement.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(int id);

    void deleteById(int id);

    void save(Customer customer);
}
