package ru.mts.animals;

import java.math.BigDecimal;

public interface Animal {
    // Геттеры полей для животных
    String getBreed();

    String getName();

    BigDecimal getCost();

    String getCharacter();
}
