package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;

@Configuration
@ComponentScan("ru")
public class Config {
    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService(){
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService){
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
