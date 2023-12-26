package ru.mts.animals;

import java.util.ArrayList;

/**
 * Интерфейс CreateAnimalService задает функционал создания животных
 * содержит в себе дефолтный, т.е реализованный метод createAnimals, который можно не переопределять в классах,
 * которые будут реализовывать этот интерфейс.
 * Так же интерфейс задает пустой метод getFactory для выбора конкретной Фабрики.
 */
public interface CreateAnimalService {


    // Дефолтный метод создания 10 уникальных животных через цикл While
    default ArrayList<Animal> createAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        int count = 0;
        AnimalFactory animalFactory;
        Animal animal;
        while (count < 10) {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal();
            animals.add(count, animal);
            System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay());
            count++;
        }
        System.out.println("\nВывод из интерфейса\n");
        return animals;
    }

    AnimalFactory getFactory();
}
