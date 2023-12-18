package ru.mts.animals.predator;

import ru.mts.animals.AbstractAnimal;

// Абстрактный класс хищного животного
public abstract class Predator extends AbstractAnimal {
    protected final String HABITS = "хищное животное "; // повадки

    void getHABITS() {
        System.out.println("Я - " + HABITS);
    }
}
