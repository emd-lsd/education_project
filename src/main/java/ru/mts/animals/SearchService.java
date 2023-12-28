package ru.mts.animals;

import java.util.ArrayList;

/**
 * Интерфейс задает функционал поиска животных: родившихся в високосный год
 * которые старше N лет
 * у которых есть дубликат.
 */
public interface SearchService {
    String[] findLeapYearName(Animal[] animals);

    Animal[] findOlderAnimal(Animal[] animals, int N);

    Animal[] findDuplicate(Animal[] animals);
}
