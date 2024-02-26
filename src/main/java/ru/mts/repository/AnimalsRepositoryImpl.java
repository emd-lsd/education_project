package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.service.CreateAnimalService;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс реализует методы репозитория по поиску животных: родившихся в високосный год
 * которые старше N лет,
 * у которых есть дубликат,
 * вывод дубликатов.
 */
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private Map<String, List<Animal>> animals;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void init() {
        animals = createAnimalService.createAnimals();
    }

    /**
     * @return animalNames - мапа имен животных с годом рождения, родившихся в високосный год
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> animalNames = new HashMap<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мап животных пуста"));
        for (Map.Entry<String, List<Animal>> entry : animals.entrySet()) {
            String animalType = entry.getKey();
            List<Animal> animalList = entry.getValue();
            for (Animal animal : animalList) {
                Optional.ofNullable(animal).orElseThrow(() -> new RuntimeException("В списке животных найден пустой элемент"));
                if (animal.getBirthDay().isLeapYear()) {
                    String key = animalType + " " + animal.getName();
                    animalNames.put(key, animal.getBirthDay());
                }
            }
        }
        return animalNames;
    }

    /**
     * @param N количество лет
     * @return olderAnimals - массив животных, старше N лет
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        Map<Animal, Integer> olderAnimals = new HashMap<>();
        int maxAge = 0;
        Animal oldestAnimal = null;
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мап животных пуста"));
        if (N <= 0) throw new RuntimeException("Количество лет N должно быть больше 0");
        for (Map.Entry<String, List<Animal>> entry : animals.entrySet()) {
            String animalType = entry.getKey();
            List<Animal> animalList = entry.getValue();
            for (Animal animal : animalList) {
                Optional.ofNullable(animal).orElseThrow(() -> new RuntimeException("В списке животных найден пустой элемент"));
                int age = Math.abs(Period.between(LocalDate.now(), animal.getBirthDay()).getYears());
                if (age > N) {
                    olderAnimals.put(animal, age);
                } else if (age > maxAge) {
                    maxAge = age;
                    oldestAnimal = animal;
                }
            }
        }
        if (olderAnimals.isEmpty()) olderAnimals.put(oldestAnimal, maxAge);
        return olderAnimals;
    }

    /**
     * @return duplicates - массив животных, у которых есть дубликат
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Integer> duplicates = new HashMap<>();
        Map<Animal, Integer> animalCountMap = new HashMap<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мапа животных пуста"));

        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                Optional.ofNullable(animal).orElseThrow(() -> new RuntimeException("В списке животных найден пустой элемент"));
                animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
            }
        }

        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.put(entry.getKey().getBreed(), duplicates.getOrDefault(entry.getKey().getBreed(), 0) + entry.getValue() - 1);
            }
        }
        return duplicates;
    }

    /**
     * Выводит на экран дубликаты животных
     */
    @Override
    public void printDuplicate() {
        Map<String, Integer> duplicates = findDuplicate();
        for (Map.Entry<String, Integer> entry : duplicates.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
