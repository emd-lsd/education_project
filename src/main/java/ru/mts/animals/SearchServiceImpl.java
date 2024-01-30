package ru.mts.animals;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс реализует методы интерфейса по поиску животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 */
public class SearchServiceImpl implements SearchService {
    /**
     * @param animals - массив животных
     * @return animalNames - массив имен животных, родившихся в високосный год
     */
    @Override
    public String[] findLeapYearName(Animal[] animals) {
        ArrayList<String> animalNames = new ArrayList<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Массив животных пуст"));
        for (Animal element : animals) {
            Optional.ofNullable(element).orElseThrow(() -> new RuntimeException("В массиве животных найден пустой элемент"));
            if (element.getBirthDay().isLeapYear()) animalNames.add(element.getName());
        }
        return animalNames.toArray(new String[0]);
    }

    /**
     * @param animals - массив животных
     * @param N       - количество лет
     * @return olderAnimals - массив животных, старше N лет
     */
    @Override
    public Animal[] findOlderAnimal(Animal[] animals, int N) {
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
     * @param animals - массив животных
     * @return duplicates - массив животных, у которых есть дубликат
     */
    @Override
    public Animal[] findDuplicate(Animal[] animals) {
        ArrayList<Animal> duplicates = new ArrayList<>();
        Map<Animal, Integer> animalCountMap = new HashMap<>();
        Optional.ofNullable(animals).orElseThrow(() -> new RuntimeException("Массив животных пуст"));

        for (Animal animal : animals) {
            Optional.ofNullable(animal).orElseThrow(() -> new RuntimeException("В массиве животных найден пустой элемент"));
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }

        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
                System.out.println("Дубликат найден " + entry.getKey().getName());
            }
        }

        return duplicates.toArray(new Animal[0]);
    }
}