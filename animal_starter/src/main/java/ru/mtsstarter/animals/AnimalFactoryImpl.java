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

    @Value("${names}")
    private String[] names;

    public AnimalFactoryImpl() {
        animal.put("HAMSTER", new Hamster("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("CAT", new Cat("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("TURTLE", new Turtle("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("DOG", new Dog("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("RABBIT", new Rabbit("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("BEAR", new Bear("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("FOX", new Fox("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("SHARK", new Shark("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("TIGER", new Tiger("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put("WOLF", new Wolf("", CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
    }

    @Override
    public Animal generateAnimal(AnimalTypes animalTypes, String name) {
        if(animalTypes!=null){
            Animal anim = animal.get(animalTypes.toString());
            anim.setName(name);
            return anim;
        }
        else{
            throw new IllegalArgumentException("AnimalTypes cannot be null");
        }
    }
}
