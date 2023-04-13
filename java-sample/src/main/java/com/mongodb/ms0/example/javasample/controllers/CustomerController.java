package com.mongodb.ms0.example.javasample.controllers;

import com.mongodb.ms0.example.javasample.models.Customer;
import com.mongodb.ms0.example.javasample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping(value = "{id}")
    public Customer getCustomerById(@PathVariable("id") String id) {
        return service.getCustomerById(id);
    }

    @GetMapping("/")
    public List<Customer> getCustomerByLastName( @RequestParam(required = false) String lastName){
        return service.getCustomerByLastName(lastName);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @PostMapping(value = "search")
    public List<Customer> customerSearch(@RequestBody Map<String, String> values) {
        return service.customerSearch(values.get("name"));
    }

}
