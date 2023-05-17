package com.cleverassets.crossroad.result;

public abstract class Event implements Comparable<Event> {

    protected int time;

    public Event(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(time, other.time);
    }

    public abstract void execute();
}
