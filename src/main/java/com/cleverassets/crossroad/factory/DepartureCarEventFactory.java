package com.cleverassets.crossroad.factory;

import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.event.DepartureCarEvent;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;

import java.util.PriorityQueue;
import java.util.Queue;

public class DepartureCarEventFactory {

    public DepartureCarEvent createDepartureCarEvent(int roadTraffic, int time, PriorityQueue<Event> priorityQueue, Queue<Car> road, Direction direction, Crossroad crossroad){
        var departureCarEvent = new DepartureCarEvent();
        departureCarEvent.setTime(time);
        departureCarEvent.setPriorityQueue(priorityQueue);
        departureCarEvent.setRoad(road);
        departureCarEvent.setRoadTraffic(roadTraffic);
        departureCarEvent.setDirection(direction);
        departureCarEvent.setCrossroad(crossroad);
        return departureCarEvent;
    }

}
