package com.cleverassets.crossroad.model.road;

import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.model.Car;
import lombok.Data;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
public class EastRoad {

    private Queue<Car> queueCar = new LinkedList<>();
    private PriorityQueue<Event> eastWestArrivalEventQueue = new PriorityQueue<>();
    private final Integer eastTraffic;
    private Integer countEastCar = 0;

    public void increaseCountEastCar(){
        countEastCar++;
    }

}
