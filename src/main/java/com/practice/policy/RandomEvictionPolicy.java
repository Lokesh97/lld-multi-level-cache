package com.practice.policy;

import java.util.ArrayList;
import java.util.List;

public class RandomEvictionPolicy implements IEvictionPolicy {
    List<String> dll;

    public RandomEvictionPolicy() {
        this.dll = new ArrayList<>();
    }

    @Override
    public void accessKey(String key) {
        dll.add(key);
    }

    @Override
    public String evict() {
        int min = 0, max = dll.size();
        int randInd = Math.min( dll.size ()-1, (int) (Math.random() * (max - min + 1) + min));

        String key = dll.get(randInd);
        dll.remove(randInd);
        return key;
    }
}
