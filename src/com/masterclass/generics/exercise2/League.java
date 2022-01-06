package com.masterclass.generics.exercise2;

import java.util.ArrayList;
import java.util.Collections;

public class League<T extends Team> {
    private final ArrayList<T> league = new ArrayList<>();
    public String name;

    public League(String name) {
        this.name = name;
    }

    public boolean add(T team) {
        if (league.contains(team)) {
            return false;
        }
        league.add(team);
        return true;
    }

    public void showLeagueTable() {
        Collections.sort(league);
        for (T t : league) {
            System.out.println(t.getName() + ": " + t.ranking());
        }
    }
}