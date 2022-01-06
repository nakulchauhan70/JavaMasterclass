package com.masterclass.oop.constructor.exercise5;

public class Floor {
    private final double width;
    private final double length;

    public Floor(double width, double length) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }

        if (length < 0) {
            this.length = 0;
        } else {
            this.length = length;
        }
    }

    public double getArea() {
        return this.width * this.length;
    }
}
