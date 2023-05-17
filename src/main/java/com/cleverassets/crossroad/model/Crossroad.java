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

    private static final Integer FIRST_TIME_CAR_ARRIVED = 0;

       private Time time = new Time();

       private NorthRoad northRoad;
       private SouthRoad southRoad;
       private WestRoad westRoad;
       private EastRoad eastRoad;
       private TrafficLight trafficLight;

       private Integer trafficLightSegmentDuration=0;

       private Integer simulationDuration;

       private Integer trafficDelay;

       private ArrivalCarEventFactory arrivalCarEventFactory = new ArrivalCarEventFactory();


    public void startSimulation() {

       generateFirstCarForNorthRoad();    // Generate first car for each direction of road
       generateFirstCarForSouthRoad();
       generateFirstCarForWestRoad();
       generateFirstCarForEastRoad();

        while (time.getCurrentTime() < simulationDuration) {
            executionEventQueueForEachRoad();  // Execute the next action in the queue
            time.increaseTime();
            changeTrafficLightColor();
        }

    }

    private void generateFirstCarForNorthRoad() {

        var northSouthArrivalEventQueue = northRoad.getArrivalEventQueue();
        var northTraffic = northRoad.getTraffic();
        var northQueueCar = northRoad.getQueueCar();

        var northRoadNextEvent = arrivalCarEventFactory
                .create(northTraffic,FIRST_TIME_CAR_ARRIVED, northSouthArrivalEventQueue, northQueueCar, Direction.NORTH, this); // Generation next car on NORTH direction

        northSouthArrivalEventQueue.add(northRoadNextEvent);
    }

    private void generateFirstCarForSouthRoad() {
        var southArrivalEventQueue = southRoad.getArrivalEventQueue();
        var southTraffic = southRoad.getTraffic();
        var southRoadQueueCar = southRoad.getQueueCar();

        var southRoadEvent = arrivalCarEventFactory
                .create(southTraffic, FIRST_TIME_CAR_ARRIVED,southArrivalEventQueue, southRoadQueueCar, Direction.SOUTH, this); // Generation next car on SOUTH direction

        southArrivalEventQueue.add(southRoadEvent);
    }

    private void generateFirstCarForWestRoad() {

        var westEastArrivalEventQueue = westRoad.getArrivalEventQueue();
        var westTraffic = westRoad.getTraffic();
        var westRoadQueueCar = westRoad.getQueueCar();

        var westRoadNextEvent = arrivalCarEventFactory
                .create(westTraffic, FIRST_TIME_CAR_ARRIVED,westEastArrivalEventQueue, westRoadQueueCar, Direction.WEST, this); // Generation next car on WEST direction

        westEastArrivalEventQueue.add(westRoadNextEvent);
    }

    private void generateFirstCarForEastRoad() {

        var eastWestArrivalEventQueue = eastRoad.getArrivalEventQueue();
        var eastTraffic = eastRoad.getTraffic();
        var eastRoadQueueCar = eastRoad.getQueueCar();

        var eastRoadNextEvent = arrivalCarEventFactory
                .create(eastTraffic, FIRST_TIME_CAR_ARRIVED,eastWestArrivalEventQueue, eastRoadQueueCar, Direction.EAST, this); // Generation next car on EAST direction

        eastWestArrivalEventQueue.add(eastRoadNextEvent);
    }


    private void executionEventQueueForEachRoad(){

        var northSouthArrivalEventQueue = northRoad.getArrivalEventQueue();
        var southNorthArrivalEventQueue = southRoad.getArrivalEventQueue();
        var westEastArrivalEventQueue = westRoad.getArrivalEventQueue();
        var eastWestArrivalEventQueue = eastRoad.getArrivalEventQueue();

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

    private void changeTrafficLightColor(){  // Switch traffic light color
        var remainingTime = timeUntilLightChange();
        if (remainingTime == 0){
            trafficLight.swapTrafficLight();
            trafficLightSegmentDuration = 0;
        } else {
            trafficLightSegmentDuration++;
        }
    }

    public void increaseCountNorthRoadCar() {
        northRoad.increaseCountCar();
    }
    public void increaseCountSouthRoadCar() {
        southRoad.increaseCountCar();
    }
    public void increaseCountWestRoadCar() {
        westRoad.increaseCountCar();
    }
    public void increaseCountEastRoadCar() {
        eastRoad.increaseCountCar();
    }


    public Integer timeUntilLightChange() {  // Calculation of how much time is left until the traffic light switch
        int remainingTime;

        if (this.trafficLight.isWestEastGreen()) {
            remainingTime =  trafficLight.getWestEastTrafficLightGreenDuration() - trafficLightSegmentDuration;
        } else {
            remainingTime =  trafficLight.getNorthSouthTrafficLightGreenDuration() - trafficLightSegmentDuration;
        }

        return remainingTime;
    }

}
