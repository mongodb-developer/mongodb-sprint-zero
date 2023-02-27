package com.mongodb.ms0.example.springdata.controllers;

import com.mongodb.ms0.example.springdata.models.Customer;
import com.mongodb.ms0.example.springdata.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value="customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    public CustomerService service;


    @GetMapping(value="{id}")
    public Customer getCustomerById(@PathVariable("id") String id) {
        Optional<Customer> customer = service.getCustomerById(id);
        return customer.orElse(null);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }


    @PostMapping(value="search")
    public List<Customer> customerSearch(@RequestBody Map<String, String> values) {
        return service.customerSearch(values.get("name"));
    }


}
