package com.cleverassets.crossroad.use_case;

import com.cleverassets.crossroad.factory.CrossroadSimulationFactory;

import java.util.logging.Logger;

public class CrossroadSimulationUseCase {

    private final Logger log = Logger.getLogger("mainLogger");
    private final CrossroadSimulationFactory crossroadSimulationFactory = new CrossroadSimulationFactory();

    public void execute(){

        var crossroad = crossroadSimulationFactory.create();
        crossroad.startSimulation();

        log.info("North count car - " + crossroad.getNorthRoad().getCountCar());
        log.info("South count car - " + crossroad.getSouthRoad().getCountCar());
        log.info("West count car - " + crossroad.getWestRoad().getCountCar());
        log.info("East count car - " + crossroad.getEastRoad().getCountCar());
    }
}
