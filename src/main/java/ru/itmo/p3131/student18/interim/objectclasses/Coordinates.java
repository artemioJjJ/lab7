package ru.itmo.p3131.student18.interim.objectclasses;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private long x;
    private long y;

    public Coordinates(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public String toString() {
        return "x: " + x + ", y:" + y;
    }
}