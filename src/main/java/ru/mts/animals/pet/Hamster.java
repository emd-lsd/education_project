package ru.mts.animals.pet;

import java.math.BigDecimal;

public class Hamster extends Pet{
    private final static String BREED = "Хомячок";
    public Hamster(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHABITS();
    }
}
