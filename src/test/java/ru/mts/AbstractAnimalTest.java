package ru.mts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.pet.Dog;
import ru.mts.animals.predator.Fox;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractAnimalTest {

    private static AbstractAnimal fox;
    private static AbstractAnimal dog;
    private static AbstractAnimal dog2;
    private static AbstractAnimal dog3;

    @BeforeAll
    static void beforeAll(){
        fox = new Fox("Foxie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10));
        dog = new Dog("Buddie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10));
        dog2 = new Dog("Freddie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10));
        dog3 = new Dog("Buddie", BigDecimal.valueOf(15000), "calm", LocalDate.of(2020, 10, 10));
    }

    @Test
    @DisplayName("Проверка на равенство самому себе")
    public void testEqualsItself(){
        assertTrue(fox.equals(fox));
    }

    @Test
    @DisplayName("Проверка на неравенство Null")
    public void testEqualsNull(){
        assertFalse(fox.equals(null));
    }

    @Test
    @DisplayName("Проверка на неравенство разных животных")
    public void testEqualsDifferentType(){
        assertFalse(fox.equals(dog));
    }

    @Test
    @DisplayName("Проверка на неравенство одинаковых животных с разными атрибутами")
    public void testEqualsSameTypeDefferentValues(){
        assertFalse(dog.equals(dog2));
    }

    @Test
    @DisplayName("Проверка на равенство одианковых животных с одинаковыми значениями")
    public void testEqualsSameTypeSameValues(){
        assertTrue(dog.equals(dog3));
    }


}
