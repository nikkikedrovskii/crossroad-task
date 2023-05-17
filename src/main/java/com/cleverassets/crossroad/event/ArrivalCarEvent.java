package com.cleverassets.crossroad.event;


import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.factory.DepartureCarEventFactory;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.PriorityQueue;
import java.util.Queue;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArrivalCarEvent extends Event {

    private PriorityQueue<Event> priorityQueue;
    private Queue<Car> road;
    private Integer roadTraffic;
    private Direction direction;
    private Crossroad crossroad;
    private DepartureCarEventFactory departureCarEventFactory = new DepartureCarEventFactory();

    @Override
    public void execute() {
        road.add(getNextCar());
        addDepartureCarEventToQueue();


    }

    private void addDepartureCarEventToQueue(){
        var departureTime = time + crossroad.getTrafficDelay();
        var departureCarEvent = departureCarEventFactory.createDepartureCarEvent(roadTraffic,departureTime,priorityQueue,road,direction,crossroad);
        priorityQueue.add(departureCarEvent);
    }

    private Car getNextCar(){
        var car = new Car();
        car.setArrivalTime(time);
        return car;
    }
}
