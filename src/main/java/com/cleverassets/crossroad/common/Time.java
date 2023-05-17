package com.cleverassets.crossroad.common;

import lombok.Data;

@Data
public class Time {

    private int currentTime=0;

    public void increaseTime() {
        currentTime++;
    }
}
