package ru.mts.service;

import ru.mts.animals.CreateAnimalService;
import ru.mts.animals.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Вызов дефолтного метода
        CreateAnimalService createAnimalService = new CreateAnimalService() {
            @Override
            public void createAnimals() {
                CreateAnimalService.super.createAnimals();
            }
        };
        createAnimalService.createAnimals();

        // Вызов метода из имплемента
        CreateAnimalServiceImpl createAnimalServiceimpl = new CreateAnimalServiceImpl();
        createAnimalServiceimpl.createAnimals();

        // Вызов метода из имплемента для N животных
        createAnimalServiceimpl.createAnimals(20);



    }
}