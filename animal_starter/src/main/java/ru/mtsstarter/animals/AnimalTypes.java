package ru.mtsstarter.animals;

import java.util.Random;

/**
 * Класс содержит перечисление всех типов животных
 */
public enum AnimalTypes {
    CAT,
    DOG,
    HAMSTER,
    RABBIT,
    TURTLE,
    BEAR,
    FOX,
    SHARK,
    TIGER,
    WOLF;

    public static AnimalTypes generateRandomType(){
        return AnimalTypes.values()[new Random().nextInt(AnimalTypes.values().length)];
    }

}
