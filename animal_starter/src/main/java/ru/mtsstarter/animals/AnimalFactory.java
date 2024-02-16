package ru.mtsstarter.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс AnimalFactory задает фабрику с методом создания жвиотных
 */
public interface AnimalFactory {

    Animal generateAnimal(AnimalTypes animalTypes, String name);

    String generateCharacter();

    LocalDate generateBirthDay();

    BigDecimal generateCost();
}
