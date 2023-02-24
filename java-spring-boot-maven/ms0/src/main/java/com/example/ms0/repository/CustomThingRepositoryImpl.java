package com.example.ms0.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.example.ms0.model.Thing;

import java.time.LocalDateTime;


@Component
public class CustomThingRepositoryImpl implements CustomThingRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void unsetExample(int a) {
        Query query = new Query(Criteria.where("a").is(a));
        Update update = new Update();
        update.unset("example");

        UpdateResult result = mongoTemplate.updateMulti(query, update, Thing.class);

        if (result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");
    }

    public void replaceMatched(int a) {
        Query query = new Query(Criteria.where("a").is(a));
        Update update = new Update();
        update.set("a", 3);
        update.set("example", false);
        update.set("time", LocalDateTime.now());
        update.set("newField", "new");

        mongoTemplate.findAndModify(query, update, Thing.class);
    }

    public void upsert(int a) {
        Query query = new Query(Criteria.where("a").is(a));
        Update update = new Update();
        update.set("updated", true);
        mongoTemplate.upsert(query, update, Thing.class);
    }

}
