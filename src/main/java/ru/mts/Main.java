package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.animals.Animal;
import ru.mts.config.Config;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepositoryImpl.class);

        //вызов findLeapYearNames
        String[] animalNames = animalsRepository.findLeapYearNames();
        for (String name : animalNames) {
            System.out.println(name);
        }
        System.out.println("Високосные годы рождений\n");

        //вызов findOlderAnimal
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(3);
        for (Animal element : olderAnimals) {
            System.out.printf("%s %s%n", element.getName(), element.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Животные старше N лет\n");

        //вызов findDuplicate
        Set<Animal> duplicates = animalsRepository.findDuplicate();

        //вызов printDuplicate
        animalsRepository.printDuplicate();
        System.out.println("Дубликаты\n");
    }
}