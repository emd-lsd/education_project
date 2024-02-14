package ru.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mts.config.Config;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.pet.Cat;
import ru.mtsstarter.animals.predator.Fox;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);

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
        Animal[] animals = new Animal[]{
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)),
                new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)),
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10))
        };
        animalsRepository.setAnimals(animals);
        Set<Animal> duplicates = animalsRepository.findDuplicate();

        //вызов printDuplicate
        animalsRepository.printDuplicate();
        System.out.println("Дубликаты\n");
    }
}