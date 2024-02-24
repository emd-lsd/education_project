package ru.mtsstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import ru.mtsstarter.animals.AnimalTypes;
import ru.mtsstarter.service.CreateAnimalServiceImpl;
import java.lang.reflect.Field;

@ContextConfiguration
public class StarterTestConfig {

    @Bean
    public CreateAnimalServiceImpl animalService() throws NoSuchFieldException, IllegalAccessException {
        CreateAnimalServiceImpl animalService = new CreateAnimalServiceImpl();
        animalService.setAnimalTypes(AnimalTypes.CAT);
        Field animalNamesField = animalService.getClass().getDeclaredField("animalNames");
        animalNamesField.setAccessible(true);
        String[] testNames = {"Bunny", "Billy"};
        animalNamesField.set(animalService, testNames);
        return animalService;
    }
}
