package com.practice.storage;

import com.practice.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements IStorage {

    private int capacity;
    private Map<String, String> keyValueMap;

    public InMemoryStorage(int capacity) {
        this.capacity = capacity;
        this.keyValueMap = new HashMap<>();
    }

    @Override
    public String get(String key) {
        if (keyValueMap.containsKey(key)) {
            return keyValueMap.get(key);
        }

        return null;
    }

    @Override
    public void set(String key, String value) {
        if (keyValueMap.containsKey(key)) {
            keyValueMap.put(key, value);
        } else if (keyValueMap.size() == capacity) {
            throw new StorageFullException();
        } else {
            keyValueMap.put(key, value);
        }
    }

    @Override
    public boolean isFull() {
        return (keyValueMap.size() == capacity);
    }

    @Override
    public void remove(String key) {
        if (keyValueMap.containsKey(key)) {
            keyValueMap.remove(key);
        }
    }
}
