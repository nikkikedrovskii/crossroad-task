package com.cleverassets.crossroad.result;


import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ArrivalCarEvent extends Event {

    private final PriorityQueue<Event> priorityQueue;
    private final Queue<Car> road;
    private final int roadTraffic;
    private final String direction;
    private final Crossroad crossroad;


    public ArrivalCarEvent(int time, PriorityQueue<Event> priorityQueue, Queue<Car> road, int roadTraffic, String direction, Crossroad crossroad) {
        super(time);
        this.priorityQueue = priorityQueue;
        this.road = road;
        this.roadTraffic = roadTraffic;
        this.direction = direction;
        this.crossroad = crossroad;
    }

    @Override
    public void execute() {
       // рабочий код
        System.out.println("На направлении " + direction + " машина в ожидании. " + "Приехала в " + time);
        var timeToNextCar = getExponentialRandomVariable(roadTraffic);
        var car = new Car();
        car.setArrivalTime(time + timeToNextCar);
        road.add(car);
        priorityQueue.add(new DepartureCarEvent((time + crossroad.getTrafficDelay()),priorityQueue,road, roadTraffic, direction, crossroad));
     // рабочий код


    }

    private int getExponentialRandomVariable(double mean) {
        double lambda = mean/ 60.0; // среднее количество машин в секунду
        double timeBetweenArrivals = Math.ceil(-Math.log(1 - Math.random()) / (lambda));
        return (int) timeBetweenArrivals;
    }
}
