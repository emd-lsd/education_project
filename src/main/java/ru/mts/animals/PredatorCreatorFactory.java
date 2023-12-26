package ru.mts.animals;

import ru.mts.animals.predator.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Класс PredatorCreatorFactory реализует интерфейс AnimalFactory.
 * В конструкторе создаются хищные животные и реализуется метод создания животных интерфейса.
 */
public class PredatorCreatorFactory implements AnimalFactory {
    Random random = new Random();
    // Генерация случайных имени, стоимости, поведения
    private final Map<Integer, Predator> animal = new HashMap<>();

    public PredatorCreatorFactory() {
        animal.put(1, new Bear(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(2, new Fox(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(3, new Shark(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(4, new Tiger(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
        animal.put(5, new Wolf(CreateAnimalServiceImpl.generateName(), CreateAnimalServiceImpl.generateCost(), CreateAnimalServiceImpl.generateCharacter(), CreateAnimalServiceImpl.generateBirthDay()));
    }

    /**
     * Переопределенный метод создания случайного хищного животного
     *
     * @return случайное хищное животное
     */
    @Override
    public Animal generateAnimal() {
        int anim = random.nextInt(animal.size()) + 1;
        return animal.get(anim);
    }
}
