package ru.mts.animals.predator;

import java.math.BigDecimal;

public class Bear extends Predator {
    private final static String BREED = "Медведь";

    public Bear(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHabits();
    }
}
