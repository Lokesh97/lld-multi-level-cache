package com.practice.layer;

import com.practice.entity.Response;
import com.practice.exception.StorageFullException;
import com.practice.policy.IEvictionPolicy;
import com.practice.storage.IStorage;

public class DefaultLayer implements ILayer {

    private IStorage storage;
    private IEvictionPolicy evictionPolicy;

    public DefaultLayer(IStorage storage, IEvictionPolicy evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public Response get(String key) {
        String value = storage.get(key);
        if (value != null) {
            this.evictionPolicy.accessKey(key);
        }
        return new Response(key, value);
    }

    @Override
    public Response set(String key, String value) {
        try {
            this.storage.set(key, value);
            this.evictionPolicy.accessKey(key);
            return null;
        } catch (StorageFullException se) {
            String keyToEvict = this.evictionPolicy.evict();
            Response response = new Response(keyToEvict, this.storage.get(keyToEvict));
            this.storage.remove(keyToEvict);
            this.set(key, value);
            return response;
        }
    }

    public boolean isFull() {
        return this.storage.isFull();
    }
}
