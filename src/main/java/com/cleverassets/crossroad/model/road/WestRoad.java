package com.cleverassets.crossroad.model.road;

import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.model.Car;
import lombok.Data;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
public class WestRoad {

    private Queue<Car> queueCar = new LinkedList<>();
    private PriorityQueue<Event> westEastArrivalEventQueue = new PriorityQueue<>();
    private final Integer westTraffic;
    private Integer countWestCar = 0;

    public void increaseCountWestCar(){
        countWestCar++;
    }

}
