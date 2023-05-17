package com.cleverassets.crossroad.model.road;

import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.model.Car;
import lombok.Data;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
public class SouthRoad {

    private Queue<Car> queueCar = new LinkedList<>();
    private PriorityQueue<Event> southNorthArrivalEventQueue = new PriorityQueue<>();
    private final Integer southTraffic;
    private Integer countSouthCar = 0;

    public void increaseCountSouthCar(){
        countSouthCar++;
    }
}
