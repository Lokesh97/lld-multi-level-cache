package com.practice.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUEvictionPolicy implements IEvictionPolicy {

    List<String> dll;
    Map<String, Integer> indexMap;

    public LRUEvictionPolicy() {
        this.dll = new ArrayList<>();
        this.indexMap = new HashMap<>();
    }

    @Override
    public void accessKey(String key) {
        if (indexMap.containsKey(key)) {
            dll.remove(indexMap.get(key));
        }
        indexMap.put(key, 0);
        dll.add(0, key);
    }

    @Override
    public String evict() {
        String key = dll.get(dll.size() - 1);
        dll.remove(dll.size() - 1);
        indexMap.remove(key);
        return key;
    }
}
