package ru.mts.animals;

import ru.mts.animals.pet.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Класс PetCreatorFactory реализует интерфейс AnimalFactory.
 * В конструкторе создаются домашние животные и реализуется метод создания животных интерфейса.
 */
public class PetCreatorFactory implements AnimalFactory {
    Random random = new Random();
    // Генерация случайных имени, стоимости, поведения
    private final Map<Integer, Pet> animal = new HashMap<>();

    public PetCreatorFactory() {
        animal.put(1, new Hamster(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(2, new Cat(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(3, new Turtle(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(4, new Dog(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(5, new Rabbit(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
    }

    /**
     * Переопределенный метод создания случайного домашнего животного
     *
     * @return случайное домашнее животное
     */
    @Override
    public Animal generateAnimal() {
        int anim = random.nextInt(animal.size()) + 1;
        return animal.get(anim);
    }
}
