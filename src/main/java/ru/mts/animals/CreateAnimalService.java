package ru.mts.animals;

import ru.mts.animals.pet.*;
import ru.mts.animals.predator.*;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Интерфейс CreateAnimalService задает функционал создания животных
 * содержит в себе дефолтный, т.е реализованный метод, который можно не переопределять в классах,
 * которые будут реализовывать этот интерфейс.
 * Так же интерфейс задает два пустых метода генерации домашних и хищных животных,
 * которые могут быть использованы для переопределения дефолтного метода в реализующем классе.
 */
public interface CreateAnimalService {


    // Дефолтный метод создания 10 уникальных животных через цикл While
    default void createAnimals() {
        String[] Names = {"Simba", "Rex", "Whiskers", "Fluffy", "Dumbo", "Jerry", "Tom", "Nemo", "Mikky"}; // клички
        String[] Characters = {"Brave", "Playful", "Calm", "Curious", "Gentle"}; // поведения
        Random random = new Random();
        int count = 0;
        AbstractAnimal ourAnimal;
        while (count < 10) {
            if (random.nextInt(2) == 0) {
                Pet pet;

                // Генерация случайных имени, стоимости, поведения
                String name = Names[random.nextInt(Names.length)];
                BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
                String character = Characters[random.nextInt(Characters.length)];

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
                ourAnimal = pet;

            } else {
                Predator predator;

                // Генерация случайных имени, стоимости, поведения
                String name = Names[random.nextInt(Names.length)];
                BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
                String character = Characters[random.nextInt(Characters.length)];

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
                ourAnimal = predator;
            }
            count++;
            System.out.println("Животное " + ourAnimal.getBreed() + " по кличке " + ourAnimal.getName() + " с характером " + ourAnimal.getCharacter() + " стоимостью " + ourAnimal.getCost() + " создано.");
        }
        System.out.println("\nВывод из интерфейса\n");
    }

    // Метод генерации домашних животных
    Pet generatePet();

    // Метод генерации хищных животных
    Predator generatePredator();
}
