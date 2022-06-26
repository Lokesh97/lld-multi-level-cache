package com.practice.service;

import com.practice.entity.Response;
import com.practice.layer.DefaultLayer;
import com.practice.layer.ILayer;
import com.practice.policy.RandomEvictionPolicy;
import com.practice.storage.InMemoryStorage;

import java.util.ArrayList;
import java.util.List;

public class MultiLevelCache implements ICache {

    private int numLayers;
    private List<Integer> capLayers;
    private List<ILayer> layers;

    public MultiLevelCache(int numLayers, List<Integer> capLayers) {
        this.numLayers = numLayers;
        this.capLayers = capLayers;
        this.layers = new ArrayList<>();
    }

    @Override
    public String get(String key) {
        Response response = null;
        for (ILayer layer : this.layers) {
            response = layer.get(key);
            if(response.getValue() != null){
                this.setInLayer(key, response.getValue(), 0);
                break;
            }
        }

        if (response != null) {
            return response.getValue();
        }
        return null;
    }

    @Override
    public void set(String key, String value) {
        this.setInLayer(key, value, 0);
    }


    public void setInLayer(String key, String value, int layerInd) {
        if (layerInd >= numLayers) {
            return;
        }

        if (layerInd >= layers.size()) {
            layers.add(layerInd, new DefaultLayer(
                    new InMemoryStorage(capLayers.get(layerInd)), new RandomEvictionPolicy()));
        }

        Response response = this.layers.get(layerInd).set(key, value);
        if (response != null) {
            this.setInLayer(response.getKey(), response.getValue(), layerInd + 1);
        }
    }
}
