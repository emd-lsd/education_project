package ru.mts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.animals.Animal;
import ru.mts.config.Config;
import ru.mts.repository.AnimalsRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalsRepositoryImplTest {

    private AnimalsRepository animalsRepository;

    @BeforeEach
    public void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        animalsRepository = context.getBean(AnimalsRepository.class);
    }

    @Test
    public void testFindLeapYearNames() {
        String[] leapYearNames = animalsRepository.findLeapYearNames();
        assertNotNull(leapYearNames);
        // Add assertions for leap year names
    }

    @Test
    public void testFindOlderAnimal() {
        int N = 5; // Change N as needed
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(N);
        assertNotNull(olderAnimals);
        // Add assertions for older animals
    }

    @Test
    public void testFindDuplicate() {
        Set<Animal> duplicates = animalsRepository.findDuplicate();
        assertNotNull(duplicates);
        // Add assertions for duplicates
    }

    @Test
    public void testPrintDuplicate() {
        // You may consider redirecting System.out to capture the printed output
        // and then asserting against that captured output.
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
    }
}

