package ru.mts.animals.pet;

import java.math.BigDecimal;

public class Cat extends Pet{
    private final static String BREED = "Котик";
    public Cat(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHABITS();
    }


}
