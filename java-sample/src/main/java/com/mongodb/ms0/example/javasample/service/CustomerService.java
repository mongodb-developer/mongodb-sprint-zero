package com.mongodb.ms0.example.javasample.service;

import com.mongodb.ms0.example.javasample.dao.CustomerDAO;
import com.mongodb.ms0.example.javasample.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO dao;


    public Customer getCustomerById(String id) {
        return dao.getCustomerById(id);
    }

    public List<Customer> getCustomerByLastName(String lastName) {
        return dao.getCustomerByLastName(lastName);
    }

    public Customer createCustomer(Customer customer) {
        return dao.createCustomer(customer);
    }

    public List<Customer> customerSearch(String name) {
        return dao.customerSearch(name);
    }
}
