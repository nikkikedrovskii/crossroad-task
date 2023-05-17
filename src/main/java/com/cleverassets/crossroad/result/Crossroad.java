package com.cleverassets.crossroad.result;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Crossroad {

       private final Queue<Car> northRoad;
       private final Queue<Car> southRoad;
       private final Queue<Car> westRoad;
       private final Queue<Car> eastRoad;

       private final PriorityQueue<Event> northSouthArrivalEventQueue;
       private final PriorityQueue<Event> southNorthArrivalEventQueue;
       private final PriorityQueue<Event> westEastArrivalEventQueue;
       private final PriorityQueue<Event> eastWestArrivalEventQueue;

       private int applicationTime;
       private final int northTraffic;
       private final int southTraffic;
       private final int westTraffic;
       private final int eastTraffic;
       private int trafficDelay;
       private int northSouthGreenTrafficLight;
       private int westEastGreenTrafficLight;
       private boolean northSouthGreen = true;
       private boolean westEastGreen = false;

       private int countNorthCar;
       private int countSouthCar;
       private int countWestCar;
       private int countEastCar;


    public Crossroad(int northTraffic, int southTraffic, int westTraffic, int eastTraffic, int trafficDelay, int northSouthGreenTrafficLight, int westEastGreenTrafficLight) {
        this.northRoad = new LinkedList<>();
        this.southRoad = new LinkedList<>();
        this.westRoad = new LinkedList<>();
        this.eastRoad = new LinkedList<>();
        this.northSouthArrivalEventQueue = new PriorityQueue<>();
        this.southNorthArrivalEventQueue = new PriorityQueue<>();
        this.westEastArrivalEventQueue = new PriorityQueue<>();
        this.eastWestArrivalEventQueue = new PriorityQueue<>();

        this.applicationTime = 0;
        this.northTraffic = northTraffic;
        this.southTraffic = southTraffic;
        this.westTraffic = westTraffic;
        this.eastTraffic = eastTraffic;
        this.trafficDelay = trafficDelay;
        this.northSouthGreenTrafficLight = northSouthGreenTrafficLight;
        this.westEastGreenTrafficLight = westEastGreenTrafficLight;
        this.countNorthCar = 0;
        this.countSouthCar = 0;
        this.countWestCar = 0;
        this.countEastCar = 0;
    }

    public void startSimulation(int simulationTime) {

       generateNextCarForNorthRoad();
       generateNextCarForSouthRoad();
       generateNextCarForWestRoad();
       generateNextCarForEastRoad();


        while (applicationTime < simulationTime) {
            var nextNorthSouthEvent = northSouthArrivalEventQueue.poll();
            nextNorthSouthEvent.execute();
            var nextSouthNorthEvent = southNorthArrivalEventQueue.poll();
            nextSouthNorthEvent.execute();
            var nextWestEastEvent = westEastArrivalEventQueue.poll();
            nextWestEastEvent.execute();
            var nextEastWestEvent = eastWestArrivalEventQueue.poll();
           nextEastWestEvent.execute();
            applicationTime++;
            int remainingTime = timeUntilLightChange();
            if (remainingTime == 0){
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println("СМЕНА СВЕТОФОРА!");
                System.out.println("Было: Северо-юг -" + northSouthGreen + " Западо-Восток - " + westEastGreen);
                this.northSouthGreen = !northSouthGreen;
                this.westEastGreen = !westEastGreen;
                System.out.println(" Стало: Северо-юг -" + northSouthGreen + " Западо-Восток - " + westEastGreen);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        }

        System.out.println(" Успело проехать на север- " + countNorthCar);
        System.out.println(" Успело проехать на юг - " + countSouthCar);
        System.out.println(" Успело проехать на запад - " + countWestCar);
        System.out.println(" Успело проехать на восток - " + countEastCar);
    }

    public int updatecountNorthCar() {
        return countNorthCar++;
    }
    public int updatecountSouthCar() {
        return countSouthCar++;
    }
    public int updatecountWestCar() {
        return countWestCar++;
    }
    public int updatecountEastCar() {
        return countEastCar++;
    }


    public int getApplicationTime() {
        return applicationTime;
    }

    public int getNorthSouthGreenTrafficLight() {
        return northSouthGreenTrafficLight;
    }

    public void setNorthSouthGreenTrafficLight(int northSouthGreenTrafficLight) {
        this.northSouthGreenTrafficLight = northSouthGreenTrafficLight;
    }

    public int getWestEastGreenTrafficLight() {
        return westEastGreenTrafficLight;
    }

    public void setWestEastGreenTrafficLight(int westEastGreenTrafficLight) {
        this.westEastGreenTrafficLight = westEastGreenTrafficLight;
    }

    public boolean isNorthSouthGreen() {
        return northSouthGreen;
    }

    public void setNorthSouthGreen(boolean northSouthGreen) {
        this.northSouthGreen = northSouthGreen;
    }

    public boolean isWestEastGreen() {
        return westEastGreen;
    }

    public void setWestEastGreen(boolean westEastGreen) {
        this.westEastGreen = westEastGreen;
    }

    public int getTrafficDelay() {
        return trafficDelay;
    }

    public void setTrafficDelay(int trafficDelay) {
        this.trafficDelay = trafficDelay;
    }

    public int timeUntilLightChange() {
        int remainingTime;

        if (this.northSouthGreen) {
            remainingTime =  (applicationTime % westEastGreenTrafficLight);
        } else {
            remainingTime =  (applicationTime % northSouthGreenTrafficLight);
        }

        return remainingTime;
    }


    public void generateNextCarForNorthRoad() {
        var nextCarEvent = new ArrivalCarEvent(0,northSouthArrivalEventQueue,northRoad, northTraffic, "СЕВЕР", this);
        northSouthArrivalEventQueue.add(nextCarEvent);
    }

    public void generateNextCarForSouthRoad() {
        var nextCarEvent = new ArrivalCarEvent(0,southNorthArrivalEventQueue,southRoad, southTraffic, "ЮГ",this);
        southNorthArrivalEventQueue.add(nextCarEvent);
    }

    public void generateNextCarForWestRoad() {
        var nextCarEvent = new ArrivalCarEvent(0,westEastArrivalEventQueue,westRoad,westTraffic, "ЗАПАД",this);
        westEastArrivalEventQueue.add(nextCarEvent);
    }

    public void generateNextCarForEastRoad() {
        var nextCarEvent = new ArrivalCarEvent(0,eastWestArrivalEventQueue,eastRoad, eastTraffic, "ВОСТОК",this);
        eastWestArrivalEventQueue.add(nextCarEvent);
    }


}
