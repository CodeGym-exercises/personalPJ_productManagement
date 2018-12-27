package com.codegym.productmanagement.service;

import com.codegym.productmanagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface ProductService {
    Product findById(int id);

    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    void deleteById(int id);

}
