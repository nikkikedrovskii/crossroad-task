package com.cleverassets.crossroad;

import com.cleverassets.crossroad.factory.CrossroadSimulationFactory;
import com.cleverassets.crossroad.factory.TrafficLightFactory;
import com.cleverassets.crossroad.properties.CrossroadConfigurationProperties;

public class CrossroadTaskApplication {

        public static void main(String[] args) {
            var trafficLightFactory = new TrafficLightFactory();
            var crossroadConfigurationProperties = new CrossroadConfigurationProperties();
            var crossroadSimulationFactory = new CrossroadSimulationFactory(crossroadConfigurationProperties,trafficLightFactory);

            var crossroad = crossroadSimulationFactory.create();
            crossroad.startSimulation();

            System.out.println("North count car - " + crossroad.getNorthRoad().getCountNorthCar());
            System.out.println("South count car - " + crossroad.getSouthRoad().getCountSouthCar());
            System.out.println("West count car - " + crossroad.getWestRoad().getCountWestCar());
            System.out.println("East count car  - " + crossroad.getEastRoad().getCountEastCar());
        }


}
