package ru.mts.animals;


import ru.mts.animals.pet.Pet;
import ru.mts.animals.predator.Predator;
import java.util.Random;

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
        return CreateAnimalService.super.generatePet();
    }

    @Override
    public Predator generatePredator() {
        return CreateAnimalService.super.generatePredator();
    }
}
