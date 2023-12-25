package ru.mts;

import ru.mts.animals.CreateAnimalService;
import ru.mts.animals.CreateAnimalServiceImpl;
import ru.mts.animals.pet.Pet;
import ru.mts.animals.predator.Predator;

public class Main {
    public static void main(String[] args) {
        // Вызов дефолтного метода c помощью анонимного класса
        /*
        CreateAnimalService createAnimalService = new CreateAnimalService() {
            @Override
            public void createAnimals() {
                CreateAnimalService.super.createAnimals();
            }

            @Override
            public Pet generatePet() {
                return null;
            }

            @Override
            public Predator generatePredator() {
                return null;
            }
        };
        createAnimalService.createAnimals();

        // Вызов метода из имплемента
        CreateAnimalServiceImpl createAnimalServiceimpl = new CreateAnimalServiceImpl();
        createAnimalServiceimpl.createAnimals();

        // Вызов метода из имплемента для N животных
        createAnimalServiceimpl.createAnimals(20);
*/
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        //createAnimalService.create(10);
        createAnimalService.createAnimals(5);

    }
}