package com.cleverassets.crossroad.factory;

import com.cleverassets.crossroad.model.Crossroad;
import com.cleverassets.crossroad.model.road.EastRoad;
import com.cleverassets.crossroad.model.road.NorthRoad;
import com.cleverassets.crossroad.model.road.SouthRoad;
import com.cleverassets.crossroad.model.road.WestRoad;
import com.cleverassets.crossroad.properties.CrossroadConfigurationProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrossroadSimulationFactory {

    private final CrossroadConfigurationProperties configurationProperties = new CrossroadConfigurationProperties();
    private final TrafficLightFactory trafficLightFactory = new TrafficLightFactory();


    public Crossroad create(){

        var crossroad = new Crossroad();

        var northRoad = new NorthRoad(configurationProperties.getNorthTraffic());
        var southRoad = new SouthRoad(configurationProperties.getSouthTraffic());
        var westRoad = new WestRoad(configurationProperties.getWestTraffic());
        var eastRoad = new EastRoad(configurationProperties.getEastTraffic());

        crossroad.setNorthRoad(northRoad);
        crossroad.setSouthRoad(southRoad);
        crossroad.setWestRoad(westRoad);
        crossroad.setEastRoad(eastRoad);
        crossroad.setTrafficDelay(configurationProperties.getTrafficDelay());
        crossroad.setTrafficLight(trafficLightFactory.create());
        crossroad.setSimulationDuration(configurationProperties.getDurationOfSimulation());
        return crossroad;
    }

}
