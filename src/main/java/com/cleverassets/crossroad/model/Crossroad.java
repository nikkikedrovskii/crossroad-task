package com.cleverassets.crossroad.model;

import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Time;
import com.cleverassets.crossroad.factory.ArrivalCarEventFactory;
import com.cleverassets.crossroad.model.road.EastRoad;
import com.cleverassets.crossroad.model.road.NorthRoad;
import com.cleverassets.crossroad.model.road.SouthRoad;
import com.cleverassets.crossroad.model.road.WestRoad;
import lombok.Data;


@Data
public class Crossroad {

       private Time time = new Time();

       private NorthRoad northRoad;
       private SouthRoad southRoad;
       private WestRoad westRoad;
       private EastRoad eastRoad;
       private TrafficLight trafficLight;

       private Integer applicationTime = 0;
       private Integer simulationDuration;

       private Integer trafficDelay;

       private ArrivalCarEventFactory arrivalCarEventFactory = new ArrivalCarEventFactory();


    public void startSimulation() {

       generateNextCarForNorthRoad();
       generateNextCarForSouthRoad();
       generateNextCarForWestRoad();
       generateNextCarForEastRoad();

        while (time.getCurrentTime() < simulationDuration) {
            executionEventQueueForEachRoad();
            time.increaseTime();
            changeTrafficLightColor();
        }

    }

    private void generateNextCarForNorthRoad() {

        var northSouthArrivalEventQueue = northRoad.getNorthSouthArrivalEventQueue();
        var northTraffic = northRoad.getNorthTraffic();
        var northQueueCar = northRoad.getQueueCar();

        var northRoadNextEvent = arrivalCarEventFactory.createArrivalCarEvent(northTraffic,0, northSouthArrivalEventQueue, northQueueCar, Direction.NORTH, this);
        northSouthArrivalEventQueue.add(northRoadNextEvent);
    }

    private void generateNextCarForSouthRoad() {
        var southArrivalEventQueue = southRoad.getSouthNorthArrivalEventQueue();
        var southTraffic = southRoad.getSouthTraffic();
        var southRoadQueueCar = southRoad.getQueueCar();

        var southRoadEvent = arrivalCarEventFactory.createArrivalCarEvent(southTraffic, 0,southArrivalEventQueue, southRoadQueueCar, Direction.SOUTH, this);
        southArrivalEventQueue.add(southRoadEvent);
    }

    private void generateNextCarForWestRoad() {

        var westEastArrivalEventQueue = westRoad.getWestEastArrivalEventQueue();
        var westTraffic = westRoad.getWestTraffic();
        var westRoadQueueCar = westRoad.getQueueCar();

        var westRoadNextEvent = arrivalCarEventFactory.createArrivalCarEvent(westTraffic, 0,westEastArrivalEventQueue, westRoadQueueCar, Direction.WEST, this);
        westEastArrivalEventQueue.add(westRoadNextEvent);
    }

    private void generateNextCarForEastRoad() {

        var eastWestArrivalEventQueue = eastRoad.getEastWestArrivalEventQueue();
        var eastTraffic = eastRoad.getEastTraffic();
        var eastRoadQueueCar = eastRoad.getQueueCar();

        var eastRoadNextEvent = arrivalCarEventFactory.createArrivalCarEvent(eastTraffic, 0,eastWestArrivalEventQueue, eastRoadQueueCar, Direction.EAST, this);
        eastWestArrivalEventQueue.add(eastRoadNextEvent);
    }


    private void executionEventQueueForEachRoad(){
        var northSouthArrivalEventQueue = northRoad.getNorthSouthArrivalEventQueue();
        var southNorthArrivalEventQueue = southRoad.getSouthNorthArrivalEventQueue();
        var westEastArrivalEventQueue = westRoad.getWestEastArrivalEventQueue();
        var eastWestArrivalEventQueue = eastRoad.getEastWestArrivalEventQueue();

        var nextSouthNorthEvent = southNorthArrivalEventQueue.poll();
        if (nextSouthNorthEvent != null) {
            nextSouthNorthEvent.execute();
        }

        var nextNorthSouthEvent = northSouthArrivalEventQueue.poll();
        if (nextNorthSouthEvent != null) {
            nextNorthSouthEvent.execute();
        }

        var nextWestEastEvent = westEastArrivalEventQueue.poll();
        if (nextWestEastEvent != null){
            nextWestEastEvent.execute();
        }
        var nextEastWestEvent = eastWestArrivalEventQueue.poll();
        if (nextEastWestEvent != null) {
            nextEastWestEvent.execute();
        }
    }

    private void changeTrafficLightColor(){
        int remainingTime = timeUntilLightChange();
        if (remainingTime == 0){
            trafficLight.swapTrafficLight();
        }
    }

    public void increaseCountNorthRoadCar() {
        northRoad.increaseCountNorthCar();
    }
    public void increaseCountSouthRoadCar() {
        southRoad.increaseCountSouthCar();
    }
    public void increaseCountWestRoadCar() {
        westRoad.increaseCountWestCar();
    }
    public void increaseCountEastRoadCar() {
        eastRoad.increaseCountEastCar();
    }


    public Integer timeUntilLightChange() {
        int remainingTime;

        if (this.trafficLight.isWestEastGreen()) {
            remainingTime =  (time.getCurrentTime() % trafficLight.getWestEastTrafficLightGreenDuration());
        } else {
            remainingTime =  (time.getCurrentTime() % trafficLight.getNorthSouthTrafficLightGreenDuration());
        }

        return remainingTime;
    }

}
