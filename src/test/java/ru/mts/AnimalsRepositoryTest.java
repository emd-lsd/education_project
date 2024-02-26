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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@DisplayName("Тесты основного функционала проекта")
public class AnimalsRepositoryTest {
    @Autowired
    AnimalsRepository animalsRepository;
    private static Map<String, List<Animal>> animalMap;
    private static Map<String, List<Animal>> duplicateMap;

    @BeforeAll
    public static void init() {
        duplicateMap = new HashMap<>();
        List<Animal> cats = new ArrayList<>();
        cats.add(new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)));
        cats.add(new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)));
        cats.add(new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)));
        List<Animal> foxes = new ArrayList<>();
        foxes.add(new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)));
        foxes.add(new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)));
        foxes.add(new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)));
        duplicateMap.put("CAT", cats);
        duplicateMap.put("FOX", foxes);

        animalMap = new HashMap<>();
        List<Animal> catList = new ArrayList<>();
        catList.add(new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)));
        catList.add(new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)));
        List<Animal> foxList = new ArrayList<>();
        foxList.add(new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)));
        foxList.add(new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)));
        animalMap.put("CAT", catList);
        animalMap.put("FOX", foxList);
    }

    @Test
    @DisplayName("Тест на нахождение животных с високосным годом рождения")
    public void testFindLeapYearName() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, animalMap);
        Map<String, LocalDate> expected = new HashMap<>();
        expected.put("CAT Kitty", LocalDate.of(2020, 10, 10));
        expected.put("CAT Bitty", LocalDate.of(2016, 11, 11));
        expected.put("FOX Broxie", LocalDate.of(2012, 10, 10));
        Map<String, LocalDate> result = assertDoesNotThrow(() -> animalsRepository.findLeapYearNames());
        assertEquals(Arrays.toString(expected.keySet().toArray()), Arrays.toString(result.keySet().toArray()));
    }

    @Test
    @DisplayName("Тест на нахождение животных дубликатов")
    public void testFindDuplicate() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, duplicateMap);
        Map<String, Integer> result = assertDoesNotThrow(() -> animalsRepository.findDuplicate());
        assertEquals(1, result.get("Cat"));
        assertEquals(1, result.get("Fox"));

    }

    @Test
    @DisplayName("Тест на нахождение старшего животного")
    public void testFindOldestAnimal() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, animalMap);
        Map<Animal, Integer> result = assertDoesNotThrow(() -> animalsRepository.findOlderAnimal(20));
        Iterator<Map.Entry<Animal, Integer>> iterator = result.entrySet().iterator();
        Integer age = iterator.next().getValue();
        assertEquals(11, age);
    }

    @Test
    @DisplayName("Тест на проверку корректности подсчёта животных старших заданного возраста")
    public void testFindCorrectOlderAnimal() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, animalMap);
        Map<Animal, Integer> result = assertDoesNotThrow(() -> animalsRepository.findOlderAnimal(1));
        assertEquals(4, result.keySet().toArray().length);
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
