package ru.mts.animals.pet;

import ru.mts.animals.AbstractAnimal;

// Абстрактный класс домашнего животного
public abstract class Pet extends AbstractAnimal {
    protected final String HABITS = "домашнее животное "; // повадки

    void getHABITS() {
        System.out.println("Я - " + HABITS);
    }
}
