package com.mongodb.ms0.example.javasample.dao;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.ms0.example.javasample.models.Customer;
import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class CustomerDAO {

    private MongoCollection<Customer> collection;

    public CustomerDAO(MongoClient client) {
        MongoDatabase database = client.getDatabase("ms0");
        this.collection = database.getCollection("customers", Customer.class);
    }

    public Customer getCustomerById(String id){
        System.out.print(this.collection.estimatedDocumentCount());
        return collection.find(eq("_id", new ObjectId(id))).first();
    }

    public List<Customer> getCustomerByLastName(String lastName) {
        List<Customer> customers = new ArrayList<>();
        collection.find(eq("lastName", lastName)).forEach(customer -> customers.add((Customer)customer));
        return customers;
    }

    public Customer createCustomer(Customer customer) {
        ObjectId id = collection.insertOne(customer).getInsertedId().asObjectId().getValue();
        customer.setId(id);
        return customer;

    }

    public List<Customer> customerSearch(String name){
        List<Customer> customers = new ArrayList<>();
        List aggregate = Arrays.asList(
                Aggregates.match(Filters.or(eq("firstName", "Smith"), eq("lastName", "Smith")))
        );
        this.collection.aggregate(aggregate).forEach(customer -> customers.add((Customer)customer));
        return customers;

    }


}
