package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.animals.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Класс реализует методы репозитория по поиску животных: родившихся в високосный год
 * которые старше N лет,
 * у которых есть дубликат,
 * вывод дубликатов.
 */
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository{

    private Animal[] animals;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService){
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void init(){
        animals = createAnimalService.createAnimals();
    }

    /**
     * Сеттер для ручного ввода животных
     * @param animals
     */
    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }

    /**
     *
     * @return animalNames - массив имен животных, родившихся в високосный год
     */
    @Override
    public String[] findLeapYearNames() {
        ArrayList<String> animalNames = new ArrayList<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Массив животных пуст"));
        for (Animal element : animals) {
            Optional.ofNullable(element).orElseThrow(() -> new RuntimeException("В массиве животных найден пустой элемент"));
            if (element.getBirthDay().isLeapYear()) animalNames.add(element.getName());
        }
        return animalNames.toArray(new String[0]);
    }

    /**
     *
     * @param N количество лет
     * @return olderAnimals - массив животных, старше N лет
     */
    @Override
    public Animal[] findOlderAnimal(int N) {
        ArrayList<Animal> olderAnimals = new ArrayList<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Массив животных пуст"));
        if (N<=0) throw new RuntimeException("Количество лет N должно быть больше 0");
        for (Animal element : animals) {
            Optional.ofNullable(element).orElseThrow(() -> new RuntimeException("В массиве животных найден пустой элемент"));
            if (Math.abs(Period.between(LocalDate.now(), element.getBirthDay()).getYears()) > N)
                olderAnimals.add(element);
        }
        return olderAnimals.toArray(new Animal[0]);
    }

    /**
     *
     * @return duplicates - массив животных, у которых есть дубликат
     */
    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> duplicates = new HashSet<>();
        Map<Animal, Integer> animalCountMap = new HashMap<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Массив животных пуст"));

        for (Animal animal : animals) {
            Optional.ofNullable(animal).orElseThrow(() -> new RuntimeException("В массиве животных найден пустой элемент"));
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }

        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
                //System.out.println("Дубликат найден " + entry.getKey().getName());
            }
        }

        return duplicates;
    }

    /**
     * Выводит на экран дубликаты животных
     */
    @Override
    public void printDuplicate() {
        Set<Animal> duplicates = findDuplicate();
        for (Animal animal : duplicates) {
            System.out.println(animal.getName());
        }
    }
}
