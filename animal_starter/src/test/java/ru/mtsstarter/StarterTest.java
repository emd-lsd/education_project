package ru.mtsstarter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.AnimalFactoryImpl;
import ru.mtsstarter.service.CreateAnimalService;
import ru.mtsstarter.service.CreateAnimalServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StarterTestConfig.class)
@DisplayName("Тесты стартер-модуля проекта")
public class StarterTest {
    @Autowired
    private CreateAnimalService createAnimalService;
    @Autowired
    private CreateAnimalServiceImpl animalService;


    @Test
    @DisplayName("Тест на совпадение количества сгенерированных животных")
    public void testAnimalArrayLength() {
        Animal[] animals = createAnimalService.createAnimals();
        assertEquals(10, animals.length);
    }

    @Test
    @DisplayName("Тест на тип животного")
    public void testAnimalType() {
        Animal[] animals = animalService.createAnimals();
        assertEquals("Cat", animals[1].getBreed());
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
