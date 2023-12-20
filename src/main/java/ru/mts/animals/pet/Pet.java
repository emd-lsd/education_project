package ru.mts.animals.pet;

import ru.mts.animals.AbstractAnimal;

/**
 * Абстрактный класс Pet наследник AbstractAnimal наследует
 * стандартные поля характеристики животных. Так же класс задает константу повадок
 * и создает метод для отображения своих повадок.
 */
public abstract class Pet extends AbstractAnimal {
    protected final String HABITS = "домашнее животное "; // повадки

    void getHabits() {
        System.out.println("Я - " + HABITS);
    }
}
