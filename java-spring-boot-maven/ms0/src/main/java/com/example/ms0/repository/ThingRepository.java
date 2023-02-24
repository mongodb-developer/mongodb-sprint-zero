package com.example.ms0.repository;

import com.example.ms0.model.Thing;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ThingRepository extends MongoRepository<Thing, String> {

    @Query("{}")
    Thing findIt();

}

