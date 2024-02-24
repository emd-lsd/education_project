package ru.mts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.config.TestConfig;
import ru.mts.repository.AnimalsRepository;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.pet.Cat;
import ru.mtsstarter.animals.predator.Fox;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@DisplayName("Тесты основного функционала проекта")
public class AnimalsRepositoryTest {
    @Autowired
    AnimalsRepository animalsRepository;
    private static Animal[] leapYearTest;
    private static Animal[] duplicates;

    @BeforeAll
    public static void init() {
        leapYearTest = new Animal[]{
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)),
                new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10))
        };
        duplicates = new Animal[]{
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)),
                new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)),
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10))
        };
    }

    @Test
    @DisplayName("Тест на нахождение животных с високосным годом рождения")
    public void testFindLeapYearName() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, leapYearTest);
        String[] expectedNames = new String[]{"Kitty", "Bitty, Broxie"};
        String[] result = assertDoesNotThrow(() -> animalsRepository.findLeapYearNames());
        assertEquals(Arrays.toString(expectedNames), Arrays.toString(result));
    }

    @Test
    @DisplayName("Тест на нахождение животных дубликатов")
    public void testFindDuplicate() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, duplicates);
        Set<Animal> result = assertDoesNotThrow(() -> animalsRepository.findDuplicate());
        assertEquals(2, result.toArray().length);
    }

    @Test
    @DisplayName("Тест на обработку пустого значения в животных")
    public void testFindDuplicateOnNullException() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, null);
        assertThrows(RuntimeException.class, () -> animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Тест на обработку пустого значения среди животных при вызове метода")
    public void testFindOlderAnimalOnNullException() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, null);
        assertThrows(RuntimeException.class, () -> animalsRepository.findOlderAnimal(3));
    }
}
