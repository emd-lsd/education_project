package ru.mts.animals;

import java.util.ArrayList;

/**
 * Интерфейс задает функционал поиска животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 */
public interface SearchService {
    ArrayList<String> findLeapYearName(ArrayList<Animal> animals);

    ArrayList<Animal> findOlderAnimal(ArrayList<Animal> animals, int N);

    ArrayList<Animal> findDuplicate(ArrayList<Animal> animals);
}
