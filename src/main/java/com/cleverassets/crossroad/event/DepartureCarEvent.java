package com.cleverassets.crossroad.event;

import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.factory.ArrivalCarEventFactory;
import com.cleverassets.crossroad.factory.DepartureCarEventFactory;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.PriorityQueue;
import java.util.Queue;

@EqualsAndHashCode(callSuper = true)
@Value
@Getter
@Setter
public class DepartureCarEvent extends Event {

     PriorityQueue<Event> priorityQueue;
     Queue<Car> road;
     Integer roadTraffic;
     Direction direction;
     Crossroad crossroad;
     ArrivalCarEventFactory arrivalCarEventFactory = new ArrivalCarEventFactory();
     DepartureCarEventFactory departureCarEventFactory = new DepartureCarEventFactory();

    @Override
    public void execute() {
        var trafficLight = crossroad.getTrafficLight();
        boolean isTrafficLightGreen = switch (direction) {   // Check the traffic lights on the selected road.
            case NORTH, SOUTH -> trafficLight.isNorthSouthGreen();
            default ->  trafficLight.isWestEastGreen();
        };

        var timeUntilChange = crossroad.timeUntilLightChange();
        var trafficDelay = crossroad.getTrafficDelay();
        var isCarHasTimeForMove = timeUntilChange > trafficDelay;  // Check if the car can pass in the remaining time while the light is green.
        var isTimeForAction = crossroad.getTime().getCurrentTime() >= time;
        var isCarCanMoveAwayFromTrafficLight = isTrafficLightGreen && isCarHasTimeForMove && isTimeForAction;

        actionsWithCarOnCrossroad(isCarCanMoveAwayFromTrafficLight);
    }

    private void actionsWithCarOnCrossroad(boolean isCarCanMoveAwayFromTrafficLight){

        if (isCarCanMoveAwayFromTrafficLight){   // The car can leave the crossroad.
            road.poll();
            increasePassedCarValue();  // Increase the number of passing cars.
            addNextArrivalCarEventToQueue(); // Create new arrival event for next car

        } else {
            addNextDepartureCarEventToQueueForSameCar();  // The car can't leave the crossroad. Postpone departure on 1 second.
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
        priorityQueue.add(arrivalCarEventFactory.create(roadTraffic,(time + timeToNext),priorityQueue,road,direction,crossroad));
    }

    private int getExponentialRandomVariable(double trafficRate) {  // Generation of the time when the next car enters the crossroad.
        double lambda = trafficRate / 60.0;
        double timeBetweenArrivals = Math.ceil(-Math.log(1 - Math.random()) / (lambda));
        return (int) timeBetweenArrivals;
    }

    private void addNextDepartureCarEventToQueueForSameCar(){
        var nextPossibleDepartureCarEvent = departureCarEventFactory.create(roadTraffic,time++,priorityQueue,road,direction,crossroad);
        priorityQueue.add(nextPossibleDepartureCarEvent);
    }

}
