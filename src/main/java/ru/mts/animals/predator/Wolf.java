package ru.mts.animals.predator;

import java.math.BigDecimal;

public class Wolf extends Predator{
    private final static String BREED = "Волк";
    public Wolf(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHABITS();
    }
}
