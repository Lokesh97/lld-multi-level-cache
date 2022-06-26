package com.practice;

import com.practice.service.ICache;
import com.practice.service.MultiLevelCache;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> capLayers = new ArrayList<>();
        capLayers.add(2);
        capLayers.add(2);

        ICache cache = new MultiLevelCache(2, capLayers);

        System.out.println(cache.get("1"));
        cache.set("1", "1");
        cache.set("2", "2");

        System.out.println("");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));

        cache.set("3", "3");
        cache.set("4", "4");
        cache.set("5", "5");

        System.out.println("");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("4"));
        System.out.println(cache.get("5"));
    }
}
