package com.example.ms0.repository;

public interface CustomThingRepository {
    void unsetExample(int a);
    void replaceMatched(int a);
    void upsert (int a);
}
