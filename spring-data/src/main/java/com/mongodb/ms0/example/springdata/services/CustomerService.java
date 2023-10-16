package com.mongodb.ms0.example.springdata.services;

import com.mongodb.ms0.example.springdata.models.Customer;
import com.mongodb.ms0.example.springdata.models.CustomerAudit;
import com.mongodb.ms0.example.springdata.repository.CustomerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MongoTemplate template;


    public Optional<Customer> getCustomerById(String id) {
        return repository.findById(id);
    }


    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> customerSearch(String name) {
        return repository.customerSearch(name);
    }

    @Transactional
    public Customer updateCustomer(String id, Customer customer) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("firstName", customer.getFirstName());
        update.set("lastName", customer.getLastName());

        CustomerAudit audit = new CustomerAudit();
        //audit.setId(new ObjectId("65280bf1e618e05b7f569a28"));
        audit.getFields().put("firstName", customer.getFirstName());
        audit.getFields().put("lastName", customer.getLastName());

        template.save(audit);


        template.updateFirst(query, update, Customer.class);
        return template.findById(id, Customer.class);



        /*
        long updated =  repository.updateCustomer(customer.getFirstName(), customer.getLastName(), id);
        if (updated > 0) {
            return customer;
        } else {
            return null;
        }
        */



    }

    public Page<Customer> getAllCustomers(int page){
        Pageable pageable = PageRequest.of(0,10);
        return repository.findAll(pageable);
    }
}
