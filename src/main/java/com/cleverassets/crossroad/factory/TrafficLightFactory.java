package com.cleverassets.crossroad.factory;

import com.cleverassets.crossroad.model.TrafficLight;
import com.cleverassets.crossroad.properties.TrafficLightConfigurationProperties;

public class TrafficLightFactory {

    private final TrafficLightConfigurationProperties configurationProperties = new TrafficLightConfigurationProperties();

    public TrafficLight create(){
        var trafficLight = new TrafficLight();
        trafficLight.setNorthSouthTrafficLightGreenDuration(configurationProperties.getNorthSouthTrafficLightGreenDuration());
        trafficLight.setWestEastTrafficLightGreenDuration(configurationProperties.getWestEastTrafficLightGreenDuration());
        return trafficLight;
    }
}
