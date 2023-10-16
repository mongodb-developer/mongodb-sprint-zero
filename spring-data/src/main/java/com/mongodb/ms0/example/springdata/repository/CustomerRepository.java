package com.mongodb.ms0.example.springdata.repository;

import com.mongodb.ms0.example.springdata.models.Customer;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {


    @Aggregation(pipeline = {"{'$match': {'$or': [{'lastName': ?0}, {'firstName': ?0}]}}"})
    List<Customer> customerSearch(String name);



    @Query("{ 'id' : ?0 }")
    Optional<Customer> findById(String id);

    @Query("{ 'id':  ?2 }")
    @Update("{'$set': {'firstName': ?0, 'lastName': ?1}}")
    long updateCustomer(String firstName,  String lastName,  String id);

}
