package com.mongodb.ms0.example.springdata.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@Document("customer_history")
public class CustomerAudit {

    private ObjectId id;
    private HashMap<String, Object> fields;
    private Date changedDate;

    public CustomerAudit(){
        this.changedDate = new Date();
        this.fields = new HashMap<>();
    }


    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
