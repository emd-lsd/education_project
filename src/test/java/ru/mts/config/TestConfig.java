package ru.mts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mtsstarter.service.CreateAnimalService;

@TestConfiguration
public class TestConfig {
    @Autowired
    CreateAnimalService createAnimalService;

    @Bean
    public AnimalsRepository animalsRepository(){
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
