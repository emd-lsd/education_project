package ru.mts.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс Animal задает поведение для животных с помощью
 * пустых методов геттеров на получение полей классов, реализующих интерфейс
 */
public interface Animal {
    // Геттеры полей для животных
    String getBreed();

    String getName();

    BigDecimal getCost();

    String getCharacter();

    LocalDate getBirthDay();
}
