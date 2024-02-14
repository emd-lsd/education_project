package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mtsstarter.service.CreateAnimalService;

@Configuration
@ComponentScan(basePackages = "ru.mts")
public class Config {
    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService){
        return  new AnimalsRepositoryImpl(createAnimalService);
    }
}
