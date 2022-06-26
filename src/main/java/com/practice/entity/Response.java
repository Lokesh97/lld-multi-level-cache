package com.practice.entity;

public class Response {
    private String key;
    private String value;

    public Response(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
