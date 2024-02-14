package ru.mts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.config.Config;
import ru.mts.repository.AnimalsRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Проверка методов AnimalsRepositoryImpl")
public class AnimalsRepositoryImplTest {

    private AnimalsRepository animalsRepository;

    @BeforeEach
    public void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        animalsRepository = context.getBean(AnimalsRepository.class);
    }

    @DisplayName("Проверка метода findLeapYearNames на null")
    @Test
    public void testFindLeapYearNames() {
        String[] leapYearNames = animalsRepository.findLeapYearNames();
        assertNotNull(leapYearNames);
    }

    @DisplayName("Проверка метода findOlderAnimal на null")
    @Test
    public void testFindOlderAnimal() {
        int N = 5;
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(N);
        assertNotNull(olderAnimals);
    }

    @DisplayName("Проверка метода findDuplicate на null")
    @Test
    public void testFindDuplicate() {
        Set<Animal> duplicates = animalsRepository.findDuplicate();
        assertNotNull(duplicates);
    }

    @DisplayName("Проверка метода printDuplicate на выброс исключения")
    @Test
    public void testPrintDuplicate() {
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
    }
}

