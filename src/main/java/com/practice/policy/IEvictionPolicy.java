package com.practice.policy;

public interface IEvictionPolicy {
    public void accessKey(String key);

    public String evict();
}
