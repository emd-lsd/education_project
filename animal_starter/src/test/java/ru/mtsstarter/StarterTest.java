package ru.mtsstarter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.AnimalFactoryImpl;
import ru.mtsstarter.animals.AnimalTypes;
import ru.mtsstarter.config.Config;
import ru.mtsstarter.service.CreateAnimalService;
import ru.mtsstarter.service.CreateAnimalServiceImpl;

import java.lang.reflect.Field;

@SpringBootTest(classes = Config.class)
@DisplayName("Тесты стартер-модуля проекта")
public class StarterTest {
    @Autowired
    private CreateAnimalService createAnimalService;


    @Test
    @DisplayName("Тест на совпадение количества сгенерированных животных")
    public void testAnimalArrayLength(){
        Animal[] animals = createAnimalService.createAnimals();
        assertEquals(10, animals.length);
    }

    @Test
    @DisplayName("Тест на тип животного")
    public void testAnimalType() throws NoSuchFieldException, IllegalAccessException {
        CreateAnimalServiceImpl animalService = new CreateAnimalServiceImpl();
        animalService.setAnimalTypes(AnimalTypes.CAT);
        Field animalNamesField = animalService.getClass().getDeclaredField("animalNames");
        animalNamesField.setAccessible(true);
        String[] testNames = {"Bunny","Billy"};
        animalNamesField.set(animalService, testNames);
        Animal[] animals = animalService.createAnimals();
        assertEquals("Cat", animals[1].getBreed());
    }

    @Test
    @DisplayName("Тест на исключение при создании животного с пустым типом")
    public void testAnimalFactoryWithNullAnimalType(){
        AnimalFactoryImpl animalFactory = new AnimalFactoryImpl();
        assertThrows(IllegalArgumentException.class,
                () -> animalFactory.generateAnimal(null, "Kitty"));
    }

    @Test
    @DisplayName("Тест на исключение при попытке воспользоваться самостоятельно созданным Сервисом создания животных, те не объявляется как bean, поэтому имена не инжектятся")
    public void testCreateAnimalsWithNullNames(){
        CreateAnimalServiceImpl animalService = new CreateAnimalServiceImpl();
        assertThrows(IllegalArgumentException.class,
                () -> animalService.createAnimals());
    }
}
