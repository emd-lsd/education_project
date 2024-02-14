package ru.mtsstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsstarter.service.CreateAnimalService;
import ru.mtsstarter.service.CreateAnimalServiceImpl;

@Configuration
@ComponentScan()
public class Config {
    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }


}
