package com.codegym.productmanagement.service;

import com.codegym.productmanagement.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    ArrayList<Customer> findAll();

    Customer findById(int id);

    void deleteById(int id);

    void save(Customer customer);

    boolean existsById(int id);
}
