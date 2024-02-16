package ru.mtsstarter.animals.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {
    private final static String BREED = "Cat";

    public Cat(String name, BigDecimal cost, String character, LocalDate birthDay) {
        this.breed = BREED;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDay = birthDay;
        //getHabits();
    }


}
