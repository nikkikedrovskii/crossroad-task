package com.cleverassets.crossroad.event;

import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.factory.ArrivalCarEventFactory;
import com.cleverassets.crossroad.factory.DepartureCarEventFactory;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.PriorityQueue;
import java.util.Queue;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartureCarEvent extends Event {

    private PriorityQueue<Event> priorityQueue;
    private Queue<Car> road;
    private Integer roadTraffic;
    private Direction direction;
    private Crossroad crossroad;
    private ArrivalCarEventFactory arrivalCarEventFactory = new ArrivalCarEventFactory();
    private DepartureCarEventFactory departureCarEventFactory = new DepartureCarEventFactory();

    @Override
    public void execute() {
        var trafficLight = crossroad.getTrafficLight();
        boolean isTrafficLightGreen = switch (direction) {
            case NORTH, SOUTH -> trafficLight.isNorthSouthGreen();
            default ->  trafficLight.isWestEastGreen();
        };

        var timeUntilChange = crossroad.timeUntilLightChange();
        var trafficDelay = crossroad.getTrafficDelay();
        var isCarHasTimeForMove = timeUntilChange > trafficDelay;
        var isTimeForAction = crossroad.getTime().getCurrentTime() >= time;
        var isCarCanMoveAwayFromTrafficLight = isTrafficLightGreen && isCarHasTimeForMove && isTimeForAction;

        actionsWithCarOnCrossroad(isCarCanMoveAwayFromTrafficLight);
    }

    private void actionsWithCarOnCrossroad(boolean isCarCanMoveAwayFromTrafficLight){

        if (isCarCanMoveAwayFromTrafficLight){
            road.poll();
            increasePassedCarValue();
            addNextArrivalCarEventToQueue();

        } else {
            addNextDepartureCarEventToQueueForSameCar();
        }
    }

    private void increasePassedCarValue(){
        switch (direction) {
            case NORTH -> crossroad.increaseCountNorthRoadCar();
            case SOUTH -> crossroad.increaseCountSouthRoadCar();
            case WEST -> crossroad.increaseCountWestRoadCar();
            case EAST -> crossroad.increaseCountEastRoadCar();
        }
    }

    private void addNextArrivalCarEventToQueue(){
        var timeToNext = getExponentialRandomVariable(roadTraffic);
        priorityQueue.add(arrivalCarEventFactory.createArrivalCarEvent(roadTraffic,(time + timeToNext),priorityQueue,road,direction,crossroad));
    }

    private int getExponentialRandomVariable(double trafficRate) {
        double lambda = trafficRate / 60.0;
        double timeBetweenArrivals = Math.ceil(-Math.log(1 - Math.random()) / (lambda));
        return (int) timeBetweenArrivals;
    }

    private void addNextDepartureCarEventToQueueForSameCar(){
        var nextPossibleDepartureCarEvent = departureCarEventFactory.createDepartureCarEvent(roadTraffic,time++,priorityQueue,road,direction,crossroad);
        priorityQueue.add(nextPossibleDepartureCarEvent);
    }

}
