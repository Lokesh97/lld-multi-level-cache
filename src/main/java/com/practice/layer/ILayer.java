package com.practice.layer;

import com.practice.entity.Response;

public interface ILayer {

    public Response get(String key);

    public Response set(String key, String value);

    public boolean isFull();
}
