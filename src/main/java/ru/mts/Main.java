package ru.mts;

import ru.mts.animals.Animal;
import ru.mts.animals.CreateAnimalServiceImpl;
import ru.mts.animals.SearchService;
import ru.mts.animals.SearchServiceImpl;
import ru.mts.animals.pet.Cat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Массив для проверки методов на null
        ArrayList<Animal> testNull = new ArrayList<>();
        testNull.add(new Cat("kit", new BigDecimal("250.00"), "Злой", LocalDate.of(2018, 5, 15)));
        //testNull.add(null);

        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();

        // Создание массива животных
        Animal[] animals = createAnimalService.createAnimals(10);
        System.out.println("\n");

        String[] animalNames = searchService.findLeapYearName(animals);
        for (String name : animalNames) {
            System.out.println(name);
        }
        System.out.println("Високосные годы рождений\n");

        Animal[] olderAnimals = searchService.findOlderAnimal(animals, 2);
        for (Animal element : olderAnimals) {
            System.out.printf("%s %s%n", element.getName(), element.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Животные старше N лет\n");

        // Дублирование массива
        ArrayList<Animal> duplicate = new ArrayList<>(Arrays.asList(animals));
        duplicate.addAll(duplicate);
        Animal[] duplicateAnimals = duplicate.toArray(new Animal[0]);
        searchService.findDuplicate(duplicateAnimals);
        System.out.println("Дубликаты животных\n");


    }
}