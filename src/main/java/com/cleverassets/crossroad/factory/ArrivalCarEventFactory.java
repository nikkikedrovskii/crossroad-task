package com.cleverassets.crossroad.factory;

import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.event.ArrivalCarEvent;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;

import java.util.PriorityQueue;
import java.util.Queue;


public class ArrivalCarEventFactory {

    public ArrivalCarEvent createArrivalCarEvent(int roadTraffic, int time, PriorityQueue<Event> priorityQueue, Queue<Car> road, Direction direction, Crossroad crossroad){
        var arrivalCarEvent = new ArrivalCarEvent();
        arrivalCarEvent.setTime(time);
        arrivalCarEvent.setPriorityQueue(priorityQueue);
        arrivalCarEvent.setRoad(road);
        arrivalCarEvent.setRoadTraffic(roadTraffic);
        arrivalCarEvent.setDirection(direction);
        arrivalCarEvent.setCrossroad(crossroad);
        return arrivalCarEvent;
    }

}
