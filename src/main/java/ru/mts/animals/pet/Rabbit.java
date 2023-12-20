package ru.mts.animals.pet;

import java.math.BigDecimal;

public class Rabbit extends Pet {
    private final static String BREED = "Кролик";

    public Rabbit(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHabits();
    }
}
