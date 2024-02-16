package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mtsstarter.animals.Animal;

import java.util.Set;

/**
 * Репозиторий задает функционал поиска животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 * вывод дубликатов животных
 */
@Repository
public interface AnimalsRepository {
    void setAnimals(Animal[] animals);
    String[] findLeapYearNames();
    Animal[] findOlderAnimal(int N);
    Set<Animal> findDuplicate();
    void printDuplicate();
}
