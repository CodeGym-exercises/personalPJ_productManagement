package com.codegym.productmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String price;

    @ManyToMany(mappedBy = "products")
    private Set<Customer> customers = new HashSet<>();

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
