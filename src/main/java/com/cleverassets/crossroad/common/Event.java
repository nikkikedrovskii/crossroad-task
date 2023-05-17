package com.cleverassets.crossroad.common;

import lombok.Data;

@Data
public abstract class Event implements Comparable<Event> {

    protected int time;

    protected Event(int time) {
        this.time = time;
    }

    protected Event() {}

    @Override
    public int compareTo(Event other) {
        return Double.compare(time, other.time);
    }

    public abstract void execute();
}
