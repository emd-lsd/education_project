package ru.mtsstarter.animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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
    protected LocalDate birthDay; // день рождения

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

    @Override
    public LocalDate getBirthDay() {
        return birthDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDay);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) obj;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDay, that.birthDay);
    }
}
