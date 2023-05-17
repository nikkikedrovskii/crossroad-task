package com.cleverassets.crossroad.event;


import com.cleverassets.crossroad.common.Direction;
import com.cleverassets.crossroad.common.Event;
import com.cleverassets.crossroad.factory.DepartureCarEventFactory;
import com.cleverassets.crossroad.model.Car;
import com.cleverassets.crossroad.model.Crossroad;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.PriorityQueue;
import java.util.Queue;

@EqualsAndHashCode(callSuper = true)
@Value
@Getter
@Setter
public class ArrivalCarEvent extends Event {

    PriorityQueue<Event> priorityQueue;
    Queue<Car> road;
    Integer roadTraffic;
    Direction direction;
    Crossroad crossroad;
    DepartureCarEventFactory departureCarEventFactory = new DepartureCarEventFactory();

    @Override
    public void execute() {
        road.add(getNextCar());
        addDepartureCarEventToQueue();
    }

    private void addDepartureCarEventToQueue(){
        var departureTime = time + crossroad.getTrafficDelay();
        var departureCarEvent = departureCarEventFactory
                .create(roadTraffic,departureTime,priorityQueue,road,direction,crossroad); // Create event of departure car
        priorityQueue.add(departureCarEvent);
    }

    private Car getNextCar(){ // Creating a new car pulling up to an intersection.
        var car = new Car();
        car.setArrivalTime(time);
        return car;
    }
}
