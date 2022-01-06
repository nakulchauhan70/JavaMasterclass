package com.masterclass.generics.exercise1;

/**
 * Created by dev on 17/10/2015.
 */
public abstract class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
