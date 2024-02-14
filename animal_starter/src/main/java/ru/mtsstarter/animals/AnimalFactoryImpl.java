package ru.mtsstarter.animals;


import org.springframework.beans.factory.annotation.Value;
import ru.mtsstarter.animals.pet.*;
import ru.mtsstarter.animals.predator.*;
import ru.mtsstarter.service.CreateAnimalServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AnimalFactoryImpl implements AnimalFactory{
    Random random = new Random();
    // Генерация случайных имени, стоимости, поведения
    private final Map<String, AbstractAnimal> animal = new HashMap<>();
    @Value("${animal.names}")
    private String[] animalNames;

    public AnimalFactoryImpl() {
        animal.put("HAMSTER", new Hamster(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("CAT", new Cat(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("TURTLE", new Turtle(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("DOG", new Dog(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("RABBIT", new Rabbit(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("BEAR", new Bear(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("FOX", new Fox(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("SHARK", new Shark(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("TIGER", new Tiger(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("WOLF", new Wolf(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
    }

    @Override
    public Animal generateAnimal(AnimalTypes animalTypes) {
        if(animalTypes!=null){
            return animal.get(animalTypes.toString());
        }
        else{
            throw new IllegalArgumentException("AnimalTypes cannot be null");
        }
    }

    @Override
    public String[] genName() {
        return animalNames;
    }
}
