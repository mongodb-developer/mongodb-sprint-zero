package com.mongodb.ms0.example.springdata.services;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.ms0.example.springdata.models.Customer;
import com.mongodb.ms0.example.springdata.models.CustomerAudit;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.stereotype.Component;



@Component
public class ChangeStreamListener implements MessageListener<ChangeStreamDocument<Document>, Customer> {


    private MongoTemplate template;


    public ChangeStreamListener(MongoTemplate template){
        super();
        this.template = template;
    }



    @Override
    public void onMessage(Message<ChangeStreamDocument<Document>, Customer> message) {
        System.out.println(message.getBody().getFirstName());
        CustomerAudit audit = new CustomerAudit();
        if (message.getRaw().getUpdateDescription() != null) {
            for (String key : message.getRaw().getUpdateDescription().getUpdatedFields().keySet()) {
                audit.getFields().put(key, message.getRaw().getUpdateDescription().getUpdatedFields().get(key).asString());
            }
            template.insert(audit);
        }

    }
}
