package com.practice.storage;

public interface IStorage {

    String get(String key);

    void set(String key, String value);

    public boolean isFull();

    public void remove(String key);
}
