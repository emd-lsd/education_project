package ru.mts.animals.predator;

import java.math.BigDecimal;

public class Tiger extends Predator {
    private final static String BREED = "Тигр";

    public Tiger(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHabits();
    }
}
