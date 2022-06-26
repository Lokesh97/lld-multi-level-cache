package com.practice.service;

public interface ICache {
    String get(String key);

    void set(String key, String value);
}
