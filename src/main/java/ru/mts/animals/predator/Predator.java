package ru.mts.animals.predator;

import ru.mts.animals.AbstractAnimal;

/**
 * Абстрактный класс Predator наследник AbstractAnimal наследует
 * стандартные поля характеристики животных. Так же класс задает константу повадок
 * и создает метод для отображения своих повадок.
 */
public abstract class Predator extends AbstractAnimal {
    protected final String HABITS = "хищное животное "; // повадки
}
