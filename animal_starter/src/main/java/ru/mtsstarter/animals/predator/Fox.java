package ru.mtsstarter.animals.predator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fox extends Predator {
    private final static String BREED = "Fox";

    public Fox(String name, BigDecimal cost, String character, LocalDate birthDay) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDay = birthDay;
        //getHabits();
    }
}
