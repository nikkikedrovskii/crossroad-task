package com.cleverassets.crossroad.model.road;

import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.model.Car;
import lombok.Data;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
public class NorthRoad {

    private Queue<Car> queueCar = new LinkedList<>();
    private PriorityQueue<Event> northSouthArrivalEventQueue = new PriorityQueue<>();
    private final Integer northTraffic;
    private Integer countNorthCar = 0;

    public void increaseCountNorthCar(){
        countNorthCar++;
    }
}
