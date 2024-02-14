package ru.mtsstarter.service;

import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.AnimalFactory;
import ru.mtsstarter.animals.AnimalTypes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Интерфейс CreateAnimalService задает функционал создания животных
 * содержит в себе дефолтный, т.е реализованный метод createAnimals, который можно не переопределять в классах,
 * которые будут реализовывать этот интерфейс.
 * Так же интерфейс задает пустой метод getFactory для выбора конкретной Фабрики.
 */
public interface CreateAnimalService {


    // Дефолтный метод создания 10 уникальных животных через цикл While
    default Animal[] createAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        int count = 0;
        AnimalFactory animalFactory;
        Animal animal;
        while (count < 10) {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal(AnimalTypes.DOG);
            animals.add(count, animal);
            System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            count++;
        }
        System.out.println("\nВывод из интерфейса\n");
        return animals.toArray(new Animal[0]);
    }

    AnimalFactory getFactory();
}
