package com.cleverassets.crossroad.model;

import lombok.Data;

@Data
public class TrafficLight {

    private Integer northSouthTrafficLightGreenDuration;
    private Integer westEastTrafficLightGreenDuration;
    private boolean northSouthGreen = true;
    private boolean westEastGreen = false;

    public void swapTrafficLight(){
        this.northSouthGreen = !northSouthGreen;
        this.westEastGreen = !westEastGreen;
    }

}
