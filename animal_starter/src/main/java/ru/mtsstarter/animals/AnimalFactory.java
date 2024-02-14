package ru.mtsstarter.animals;

/**
 * Интерфейс AnimalFactory задает фабрику с методом создания жвиотных
 */
public interface AnimalFactory {

    Animal generateAnimal(AnimalTypes animalTypes);
    String[] genName();
}
