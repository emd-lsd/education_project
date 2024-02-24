package ru.mtsstarter.config;
import org.springframework.context.annotation.*;
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
