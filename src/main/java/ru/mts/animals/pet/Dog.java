package ru.mts.animals.pet;

import java.math.BigDecimal;

public class Dog extends Pet {
    private final static String BREED = "Собачка";

    public Dog(String name, BigDecimal cost, String character) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        getHabits();
    }


}
