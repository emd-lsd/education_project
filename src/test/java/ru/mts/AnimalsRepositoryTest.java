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
import ru.mtsstarter.animals.pet.Dog;
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
    private static List<Animal> animalList;
    private static List<Animal> lowCosterAnimals;

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
        catList.add(new Cat("Kitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)));
        List<Animal> foxList = new ArrayList<>();
        foxList.add(new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)));
        foxList.add(new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)));
        animalMap.put("CAT", catList);
        animalMap.put("FOX", foxList);

        animalList = new ArrayList<>();
        animalList.add(new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)));
        animalList.add(new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)));
        animalList.add(new Fox("Foxie", BigDecimal.valueOf(100000), "calm", LocalDate.of(2015, 10, 10)));

        lowCosterAnimals = new ArrayList<>();
        lowCosterAnimals.add(new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)));
        lowCosterAnimals.add(new Cat("Bitty", BigDecimal.valueOf(10000), "angry", LocalDate.of(2016, 11, 11)));
        lowCosterAnimals.add(new Fox("Foxie", BigDecimal.valueOf(100000), "calm", LocalDate.of(2015, 10, 10)));
        lowCosterAnimals.add(new Dog("Puppy", BigDecimal.valueOf(5000), "calm", LocalDate.of(2015, 10, 10)));
    }

    @Test
    @DisplayName("Тест на нахождение животных с високосным годом рождения")
    public void testFindLeapYearName() throws NoSuchFieldException, IllegalAccessException {
        Field animalsField = animalsRepository.getClass().getDeclaredField("animals");
        animalsField.setAccessible(true);
        animalsField.set(animalsRepository, animalMap);
        Map<String, LocalDate> expected = new HashMap<>();
        expected.put("CAT Kitty", LocalDate.of(2016, 11, 11));
        expected.put("CAT Kitty", LocalDate.of(2020, 10, 10));
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
        Map<String, List<Animal>> result = assertDoesNotThrow(() -> animalsRepository.findDuplicate());
        assertEquals(1, result.get("CAT").toArray().length);
        assertEquals(1,result.get("FOX").toArray().length);

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
        Map<Animal, Integer> result = assertDoesNotThrow(() -> animalsRepository.findOlderAnimal(100));
        System.out.println(Arrays.toString(result.entrySet().toArray()));
        assertEquals(1, result.keySet().toArray().length);
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

    @Test
    @DisplayName("Тест на проверку корректной работы метода вычисления среднего возраста животных")
    public void testFindAverageAgeCorrectWork(){
        Double averageAge = assertDoesNotThrow(() -> animalsRepository.findAverageAge(animalList));
        assertEquals(Double.valueOf(6), averageAge);
    }

    @Test
    @DisplayName("Тест на проверку корректной работы метода нахождения животных с возрастом > 5 лет и стоимостью > средней стоимости всех")
    public void testFindOldAndExpensiveCorrectWork(){
        List<Animal> animals = assertDoesNotThrow(() -> animalsRepository.findOldAndExpensive(animalList));
        assertEquals(1, animals.toArray().length);
    }

    @Test
    @DisplayName("Тест на проверку корректной работы метода нахождению 3х самых дешевых животных")
    public void testFindMinConstAnimalsCorrectWork(){
        List<String> animalNames = assertDoesNotThrow(() -> animalsRepository.findMinConstAnimals(lowCosterAnimals));
        assertEquals(3, animalNames.toArray().length);
    }
}
