package com.cleverassets.crossroad.common;

import lombok.Data;

@Data
public abstract class Event implements Comparable<Event> {

    protected int time;

    @Override
    public int compareTo(Event other) {
        return Double.compare(time, other.time);
    }

    public abstract void execute();
}
