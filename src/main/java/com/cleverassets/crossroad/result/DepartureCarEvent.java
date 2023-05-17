package com.cleverassets.crossroad.result;

import java.util.PriorityQueue;
import java.util.Queue;

public class DepartureCarEvent extends Event{

    private final PriorityQueue<Event> priorityQueue;
    private final Queue<Car> road;
    private final int roadTraffic;
    private final String direction;
    private Crossroad crossroad;

    public DepartureCarEvent(int time, PriorityQueue<Event> priorityQueue, Queue<Car> road, int roadTraffic, String direction, Crossroad crossroad) {
        super(time);
        this.priorityQueue = priorityQueue;
        this.road = road;
        this.roadTraffic = roadTraffic;
        this.direction = direction;
        this.crossroad = crossroad;
    }

    @Override
    public void execute() {
        // Рабочий код
        boolean isGreen = switch (direction) {
            case "СЕВЕР", "ЮГ" -> crossroad.isNorthSouthGreen();
            default ->  crossroad.isWestEastGreen();
        };
        var timeUntilChange = crossroad.timeUntilLightChange();
        var trafficDelay = crossroad.getTrafficDelay();
        var isCarMakeTime = timeUntilChange >=trafficDelay;
        var hasCarInROad = road.size() > 0;
        var isTimeForAction = crossroad.getApplicationTime() >= time;

        if (isGreen && (isCarMakeTime && hasCarInROad) && (isTimeForAction)){
            System.out.println("На направлении " + direction + " проехала машина. " + "Уехала в " + time);
            road.poll();

            switch (direction) {
                case "СЕВЕР" -> crossroad.updatecountNorthCar();
                case "ЮГ" -> crossroad.updatecountSouthCar();
                case "ЗАПАД" -> crossroad.updatecountWestCar();
                case "ВОСТОК" -> crossroad.updatecountEastCar();
            }

           var timeToNextCar = getExponentialRandomVariable(roadTraffic);
            priorityQueue.add(new ArrivalCarEvent((time + timeToNextCar),priorityQueue,road, roadTraffic, direction, crossroad));

        } else {
            priorityQueue.add(new DepartureCarEvent((time),priorityQueue,road, roadTraffic, direction, crossroad));
        }
        // Рабочий код
    }

    private int getExponentialRandomVariable(double mean) {
        double lambda = mean/ 60.0; // среднее количество машин в секунду
        double timeBetweenArrivals = Math.ceil(-Math.log(1 - Math.random()) / (lambda));
        return (int) timeBetweenArrivals;
    }
}
