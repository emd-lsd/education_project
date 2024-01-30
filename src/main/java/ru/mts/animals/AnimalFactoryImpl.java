package ru.mts.animals;

import ru.mts.animals.pet.*;
import ru.mts.animals.predator.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AnimalFactoryImpl implements AnimalFactory{
    Random random = new Random();
    // Генерация случайных имени, стоимости, поведения
    private final Map<Integer, AbstractAnimal> animal = new HashMap<>();

    public AnimalFactoryImpl() {
        animal.put(1, new Hamster(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(2, new Cat(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(3, new Turtle(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(4, new Dog(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(5, new Rabbit(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(6, new Bear(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(7, new Fox(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(8, new Shark(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(9, new Tiger(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(10, new Wolf(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
    }

    @Override
    public Animal generateAnimal() {
        int anim = random.nextInt(animal.size()) + 1;
        return animal.get(anim);
    }
}
