package ru.mtsstarter.animals.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {
    private final static String BREED = "Dog";

    public Dog(String name, BigDecimal cost, String character, LocalDate birthDay) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDay = birthDay;
    }
}
