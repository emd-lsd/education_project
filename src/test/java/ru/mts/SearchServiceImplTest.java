package ru.mts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.animals.Animal;
import ru.mts.animals.SearchServiceImpl;
import ru.mts.animals.pet.Cat;
import ru.mts.animals.predator.Fox;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceImplTest {

    private static SearchServiceImpl searchService;
    private static Animal[] leapYearTest;
    private static Animal[] animalsWithNull;
    private static Animal[] duplicates;


    @BeforeAll
    static void beforeAll(){
        searchService = new SearchServiceImpl();
        leapYearTest = new Animal[]{
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)),
                new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10))
        };
        animalsWithNull = new Animal[]{
                new Cat("Kitty", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10)),
                new Cat("Bitty", BigDecimal.valueOf(15000), "angry", LocalDate.of(2016, 11, 11)),
                new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2015, 10, 10)),
                new Fox("Broxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2012, 10, 10)),
                null
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
    @DisplayName("Проверка корректности работы метода findLeapYearName")
    public void testFindLeapYearName(){
        String[] resultNames = searchService.findLeapYearName(leapYearTest);
        String[] expectedNames = new String[]{"Kitty", "Bitty, Broxie"};
        assertEquals(Arrays.toString(resultNames), Arrays.toString(expectedNames));
    }

    @Test
    @DisplayName("Проверка работы метода findLeapYearName с null")
    public void testFindLeapYearNameWithNull(){
        Animal[] arrWithNull = null;
        assertThrows(RuntimeException.class, ()-> searchService.findLeapYearName(arrWithNull));
    }

    @Test
    @DisplayName("Проверка работы метода findLeapYearName с пустым массивом")
    public void testFindLeapYearNameWithEmptyArray(){
        Animal[] empty = new Animal[]{};
        String[] resultNames = searchService.findLeapYearName(empty);
        assertEquals(Arrays.toString(resultNames), "[]");
    }

    @Test
    @DisplayName("Проверка работы метода findLeapYearName с пустым элементом в массиве")
    public void testFindLeapYearNameWithEmptyElement(){
        assertThrows(RuntimeException.class, ()-> searchService.findLeapYearName(animalsWithNull));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 25})
    @DisplayName("Проверка корректности работы метода")
    public void testFindOlderAnimal(int val){
        Animal[] result = searchService.findOlderAnimal(leapYearTest, val);
        switch (val){
            case 2:
                assertEquals(4, result.length);
                break;
            case 5:
                assertEquals(3, result.length);
                break;
            case 10:
                assertEquals(1, result.length);
                break;
            case 25:
                assertEquals(0, result.length);
                break;
            default:
                throw new RuntimeException("Incorrect input");

        }
    }

    @Test
    @DisplayName("Проверка работы метода findOlderAnimal с null массивом")
    public void testFindOlderAnimalWithNull(){
        Animal[] arrWithNull = null;
        assertThrows(RuntimeException.class, ()-> searchService.findOlderAnimal(arrWithNull, 5));
    }

    @Test
    @DisplayName("Проверка работы метода findOlderAnimal с пустым элементом в массиве")
    public void testFindOlderAnimalWithEmptyElement(){
        assertThrows(RuntimeException.class, ()-> searchService.findOlderAnimal(animalsWithNull, 5));
    }

    @Test
    @DisplayName("Проверка работы метода findOlderAnimal с не положительным числом лет")
    public void testFindOlderAnimalWithNotNaturalAge(){
        assertThrows(RuntimeException.class, ()-> searchService.findOlderAnimal(leapYearTest, 0));
        assertThrows(RuntimeException.class, ()-> searchService.findOlderAnimal(leapYearTest, -1));
    }

    @Test
    @DisplayName("Проверка корректности работы метода findDuplicate")
    public void testFindDuplicate(){
        Animal[] result = searchService.findDuplicate(duplicates);
        assertEquals(2, result.length);
        result = searchService.findDuplicate(leapYearTest);
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("Проверка работы метода findDuplicate c массивом null")
    public void testFindDuplicateWithNullArray(){
        Animal[] arrWithNull = null;
        assertThrows(RuntimeException.class, ()-> searchService.findDuplicate(arrWithNull));
    }

    @Test
    @DisplayName("Проверка работы метода findDuplicate c массивом null")
    public void testFindDuplicateWithNullElement(){
        assertThrows(RuntimeException.class, ()-> searchService.findDuplicate(animalsWithNull));
    }

}
