package ru.mts.animals;

import java.math.BigDecimal;

/**
 * Абстрактный класс AbstractAnimal, реализующий интерфейс Animal
 * задает различные поля-характеристики для классов наследников и реализует
 * методы геттеры полей интерфейса.
 */
public abstract class AbstractAnimal implements Animal {

    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // поведение

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }
}
