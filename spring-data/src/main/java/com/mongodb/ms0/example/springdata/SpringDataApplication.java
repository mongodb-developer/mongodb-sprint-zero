package com.mongodb.ms0.example.springdata;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.ms0.example.springdata.models.Customer;
import com.mongodb.ms0.example.springdata.services.ChangeStreamListener;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@SpringBootApplication
public class SpringDataApplication {

    @Bean
    MessageListenerContainer messageListenerContainer(MongoTemplate template) {
        return new DefaultMessageListenerContainer(template);
    }




    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataApplication.class, args);

        MongoTemplate template = context.getBean(MongoTemplate.class);
        MessageListenerContainer container = context.getBean(MessageListenerContainer.class);
        ChangeStreamListener listener = context.getBean(ChangeStreamListener.class);

        ChangeStreamRequest<Customer> request = ChangeStreamRequest.builder()
                .collection("customers")
                .publishTo(listener)
                .database("providerPref")
                .filter()
                .build();

        container.register(request, Customer.class);
        container.start();







    }

}
