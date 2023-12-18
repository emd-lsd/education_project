package ru.mts.animals;

import ru.mts.animals.pet.*;
import ru.mts.animals.predator.*;

import java.math.BigDecimal;
import java.util.Random;

public interface CreateAnimalService {

    String[] NAMES = {"Simba", "Rex", "Whiskers", "Fluffy", "Dumbo", "Jerry", "Tom", "Nemo", "Mikky"}; // клички
    String[] CHARACTERS = {"Brave", "Playful", "Calm", "Curious", "Gentle"}; // поведения

    // Дефолтный метод создания 10 уникальных животных через цикл While
    default void createAnimals() {
        Random random = new Random();
        int count = 0;
        AbstractAnimal ourAnimal;
        while (count < 10) {
            if (random.nextInt(2) == 0) ourAnimal = generatePet();
            else ourAnimal = generatePredator();
            count++;
            System.out.println("Животное " + ourAnimal.getBreed() + " по кличке " + ourAnimal.getName() + " с характером " + ourAnimal.getCharacter() + " стоимостью " + ourAnimal.getCost() + " создано.");
        }
        System.out.println("\nВывод из интерфейса\n");
    }

    // Метод генерации домашних животных
    default Pet generatePet() {
        Random random = new Random();
        Pet pet;

        // Генерация случайных имени, стоимости, поведения
        String name = NAMES[random.nextInt(NAMES.length)];
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        String character = CHARACTERS[random.nextInt(CHARACTERS.length)];

        switch (random.nextInt(5)) {
            case 0: {
                pet = new Cat(name, cost, character);
                break;
            }
            case 1: {
                pet = new Dog(name, cost, character);
                break;
            }
            case 2: {
                pet = new Rabbit(name, cost, character);
                break;
            }
            case 3: {
                pet = new Turtle(name, cost, character);
                break;
            }
            default: {
                pet = new Hamster(name, cost, character);
                break;
            }
        }

        return pet;
    }

    // Метод генерации хищных животных
    default Predator generatePredator() {
        Random random = new Random();
        Predator predator;

        // Генерация случайных имени, стоимости, поведения
        String name = NAMES[random.nextInt(NAMES.length)];
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        String character = CHARACTERS[random.nextInt(CHARACTERS.length)];

        switch (random.nextInt(5)) {
            case 0: {
                predator = new Shark(name, cost, character);
                break;
            }
            case 1: {
                predator = new Wolf(name, cost, character);
                break;
            }
            case 2: {
                predator = new Bear(name, cost, character);
                break;
            }
            case 3: {
                predator = new Fox(name, cost, character);
                break;
            }
            default: {
                predator = new Tiger(name, cost, character);
                break;
            }
        }

        return predator;
    }
}
