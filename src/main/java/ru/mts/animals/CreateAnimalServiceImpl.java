package ru.mts.animals;


import ru.mts.animals.pet.*;
import ru.mts.animals.predator.*;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Класс CreateAnimalServiceImpl реализует интерфейс CreateAnimalService.
 * Внутри класса переопределен дефолтный метод интерфейса для реализаиии его через другой цикл
 * с использованием распределенной генерации животных через методы по контракту.
 * Так же этот метод перегружен введением атрибута целого числа - желаемого числа создаваемых животных
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    //Перегруженный метод создания животных по заданному целому числу
    public void createAnimals(int amount) {
        Random random = new Random();
        AbstractAnimal ourAnimal;
        for (int i = 0; i < amount; i++) {
            if (random.nextInt(2) == 0) ourAnimal = generatePet();
            else ourAnimal = generatePredator();
            System.out.println("Животное " + ourAnimal.getBreed() + " по кличке " + ourAnimal.getName() + " с характером " + ourAnimal.getCharacter() + " стоимостью " + ourAnimal.getCost() + " создано.");
        }
        System.out.println("\nВывод " + amount + " животных из имплемента\n");
    }

    // Переопределенный метод создания 10 животных через цикл do while
    @Override
    public void createAnimals() {
        Random random = new Random();
        int count = 0;
        AbstractAnimal ourAnimal;
        do {
            if (random.nextInt(2) == 0) ourAnimal = generatePet();
            else ourAnimal = generatePredator();
            count++;
            System.out.println("Животное " + ourAnimal.getBreed() + " по кличке " + ourAnimal.getName() + " с характером " + ourAnimal.getCharacter() + " стоимостью " + ourAnimal.getCost() + " создано.");
        } while (count < 10);
        System.out.println("\nВывод 10 животных из имплемента\n");
    }

    @Override
    public Pet generatePet() {
        Random random = new Random();
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

        return pet;
    }

    @Override
    public Predator generatePredator() {
        Random random = new Random();
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

        return predator;
    }
}
