package com.cleverassets.crossroad.model.road;

import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.model.Car;
import lombok.Data;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Data
public abstract class Road {
    private Queue<Car> queueCar = new LinkedList<>();
    private PriorityQueue<Event> arrivalEventQueue = new PriorityQueue<>();
    private final Integer traffic;
    private Integer countCar = 0;

    public void increaseCountCar(){
        countCar++;
    }
}
