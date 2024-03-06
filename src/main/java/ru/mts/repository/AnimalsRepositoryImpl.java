package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.service.CreateAnimalService;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мап животных пуста"));
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .filter(animal -> Optional.ofNullable(animal).isPresent() && animal.getBirthDay().isLeapYear())
                        .map(animal -> {
                            String key = entry.getKey() + " " + animal.getName();
                            return new AbstractMap.SimpleEntry<>(key, animal.getBirthDay());
                        })
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (existingValue, newValue) -> existingValue)); // при возникновении дубликата по ключу оставляем существующее значение
    }

    /**
     * @param N количество лет
     * @return olderAnimals - массив животных, старше N лет
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мап животных пуста"));
        if (N <= 0) throw new RuntimeException("Количество лет N должно быть больше 0");
        Map<Animal, Integer> olderAnimals = animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.groupingBy(
                        animal -> animal,
                        Collectors.mapping(
                                animal -> Math.abs(Period.between(LocalDate.now(), animal.getBirthDay()).getYears()),
                                Collectors.reducing(0, Integer::max)
                        )
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > N)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        if(olderAnimals.isEmpty()){
            Animal oldestAnimal = animals.entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream())
                    .min(Comparator.comparing(animal -> Period.between(LocalDate.now(), animal.getBirthDay()).getYears()))
                    .orElseThrow(() -> new RuntimeException("Мапа животных пуста"));
            int age = Math.abs(Period.between(LocalDate.now(), oldestAnimal.getBirthDay()).getYears());
            olderAnimals.put(oldestAnimal, age);
        }
        return olderAnimals;
    }

    /**
     * @return duplicates - массив животных, у которых есть дубликат
     */
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Мапа животных пуста"));
        return animals.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        value -> value.getValue().stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().filter(entry -> entry.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())));
    }

    /**
     * Выводит на экран дубликаты животных
     */
    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> duplicates = findDuplicate();
        for (Map.Entry<String, List<Animal>> entry : duplicates.entrySet()) {
            String key = entry.getKey();
            List<Animal> animalList = entry.getValue();
            System.out.println("Дубликаты " + key + ": ");
            for(Animal animal : animalList){
                System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }
    }

    /**
     *  Метод поиска среднего возраста среди животных
     * @param animalList список животных на вход
     * @return возвращает
     */
    @Override
    public Double findAverageAge(List<Animal> animalList) {
        Optional.ofNullable(animalList).orElseThrow(() -> new RuntimeException("Список животных пуст"));
        Double averageAge = animalList.stream()
                .mapToLong(animal -> Math.abs(Period.between(LocalDate.now(), animal.getBirthDay()).getYears()))
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
        System.out.println("Средний возраст животных " + averageAge + "\n");
        return averageAge;
    }

    /**
     *  Метод поиска животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных
     * @param animalList список животных на вход
     * @return отсортированный список животных по дню рождения (по возрастанию)
     */
    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList) {
        Optional.ofNullable(animalList).orElseThrow(() -> new RuntimeException("Список животных пуст"));
        BigDecimal averageCost = animalList.stream()
                .map(animal -> animal.getCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animalList.size()), 2, BigDecimal.ROUND_HALF_UP);
        return animalList.stream()
                .filter(animal -> Math.abs(Period.between(LocalDate.now(), animal.getBirthDay()).getYears()) > 5 && animal.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDay))
                .collect(Collectors.toList());
    }

    /**
     *  Метод поиска 3х самых дешевых животных
     * @param animalList список животных на вход
     * @return список имен животных в обратном алфавитном порядке на вывод
     */
    @Override
    public List<String> findMinConstAnimals(List<Animal> animalList) {
        Optional.ofNullable(animalList).orElseThrow(() -> new RuntimeException("Список животных пуст"));
        return animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     *  Метод преобразует мапу животных в один список животных
     * @return общий список животных
     */
    @Override
    public List<Animal> convertMapToList() {
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
}
