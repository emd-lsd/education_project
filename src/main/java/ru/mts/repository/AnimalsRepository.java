package ru.mts.repository;

import ru.mtsstarter.animals.Animal;
import java.time.LocalDate;
import java.util.Map;


/**
 * Репозиторий задает функционал поиска животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 * вывод дубликатов животных
 */
public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames();
    Map<Animal, Integer> findOlderAnimal(int N);
    Map<String, Integer> findDuplicate();
    void printDuplicate();
}
