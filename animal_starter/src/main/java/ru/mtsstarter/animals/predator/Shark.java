package ru.mtsstarter.animals.predator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shark extends Predator {
    private final static String BREED = "Shark";

    public Shark(String name, BigDecimal cost, String character, LocalDate birthDay) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDay = birthDay;
    }
}
