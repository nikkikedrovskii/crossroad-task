package com.cleverassets.crossroad.result;

public class Time {

    private int currentTime;

    public Time() {
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void advanceTime(int time) {
        currentTime += time;
    }
}
