package ru.mts.animals.predator;

import java.math.BigDecimal;

public class Fox extends Predator{
    private final static String BREED = "Лиса";
    public Fox(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHABITS();
    }
}
