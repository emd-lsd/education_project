package ru.mtsstarter.animals.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rabbit extends Pet {
    private final static String BREED = "Rabbit";

    public Rabbit(String name, BigDecimal cost, String character, LocalDate birthDay) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDay = birthDay;
    }
}
