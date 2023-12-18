package ru.mts.animals.pet;

import java.math.BigDecimal;

public class Turtle extends Pet{
    private final static String BREED = "Черепаха";
    public Turtle(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHABITS();
    }
}
