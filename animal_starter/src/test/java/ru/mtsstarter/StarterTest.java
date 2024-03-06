package ru.mtsstarter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.AnimalFactoryImpl;
import ru.mtsstarter.service.CreateAnimalServiceImpl;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StarterTestConfig.class)
@DisplayName("Тесты стартер-модуля проекта")
public class StarterTest {
    @Autowired
    private CreateAnimalServiceImpl animalService;

    @Test
    @DisplayName("Тест на совпадение количества сгенерированных животных")
    public void testAnimalArrayLength() {
        Map<String, List<Animal>> animalMap = animalService.createAnimals();
        int totalCount = 0;
        for (List<Animal> animalList : animalMap.values()) {
            totalCount += animalList.size();
        }
        assertEquals(10, totalCount);
    }

    @Test
    @DisplayName("Тест на тип животного")
    public void testAnimalType() {
        Map<String, List<Animal>> animalMap = animalService.createAnimals();
        assertTrue(animalMap.containsKey("CAT"));
    }

    @Test
    @DisplayName("Тест на исключение при создании животного с пустым типом")
    public void testAnimalFactoryWithNullAnimalType() {
        AnimalFactoryImpl animalFactory = new AnimalFactoryImpl();
        assertThrows(IllegalArgumentException.class,
                () -> animalFactory.generateAnimal(null, "Kitty"));
    }

    @Test
    @DisplayName("Тест на исключение при попытке создать животных с отрицательным числом аргументом")
    public void testCreateAnimalsWithNegativeNumber() {
        assertThrows(RuntimeException.class,
                () -> animalService.createAnimals(-5));
    }
}
