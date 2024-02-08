package ru.mts.repository;

import ru.mts.animals.Animal;

import java.util.Set;

/**
 * Репозиторий задает функционал поиска животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 * вывод дубликатов животных
 */
public interface AnimalsRepository {
    String[] findLeapYearNames();
    Animal[] findOlderAnimal(int N);
    Set<Animal> findDuplicate();
    void printDuplicate();
}
