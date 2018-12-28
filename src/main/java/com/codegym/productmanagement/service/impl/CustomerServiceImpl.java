package com.codegym.productmanagement.service.impl;

import com.codegym.productmanagement.model.Customer;
import com.codegym.productmanagement.repository.CustomerRepository;
import com.codegym.productmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(int id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }
}
