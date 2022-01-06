package com.masterclass.oop.composition.exercise1;

public class Car extends Vehicle {
    private final int doors;
    private final int engineCapacity;

    public Car(String name, int doors, int engineCapacity) {
        super(name);
        this.doors = doors;
        this.engineCapacity = engineCapacity;
    }
}
