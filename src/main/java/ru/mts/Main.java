package ru.mts;

import ru.mts.animals.Animal;
import ru.mts.animals.CreateAnimalServiceImpl;
import ru.mts.animals.SearchService;
import ru.mts.animals.SearchServiceImpl;
import ru.mts.animals.pet.Cat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Массив для проверки животных для дубликат
        ArrayList<Animal> testDuplicate = new ArrayList<>();
        testDuplicate.add(new Cat("kit", new BigDecimal("250.00"), "Злой", LocalDate.of(2018, 5, 15)));
        testDuplicate.add(new Cat("kit", new BigDecimal("250.00"), "Злой", LocalDate.of(2018, 5, 15)));

        // Массив для проверки методов на null
        ArrayList<Animal> testNull = new ArrayList<>();
        testNull.add(new Cat("kit", new BigDecimal("250.00"), "Злой", LocalDate.of(2018, 5, 15)));
        testNull.add(null);

        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();

        // Создание массива животных
        ArrayList<Animal> animals = createAnimalService.createAnimals(10);
        System.out.println("\n");

        ArrayList<String> animalNames = searchService.findLeapYearName(animals);
        for (String name : animalNames) {
            System.out.println(name);
        }
        System.out.println("Високосные годы рождений\n");

        ArrayList<Animal> olderAnimals = searchService.findOlderAnimal(animals, 2);
        for (Animal element : olderAnimals) {
            System.out.printf("%s %s%n", element.getName(), element.getBirthDay());
        }
        System.out.println("Животные старше N лет\n");

        ArrayList<Animal> duplicates = searchService.findDuplicate(animals);
        for (Animal element : testDuplicate) {
            System.out.println(element.getName());
        }
        System.out.println("Дубликаты животных\n");


    }
}